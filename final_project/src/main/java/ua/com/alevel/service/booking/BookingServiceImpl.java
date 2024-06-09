package ua.com.alevel.service.booking;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.persistence.entity.BookingStatus;
import ua.com.alevel.persistence.entity.Passenger;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.repository.BookingRepository;
import ua.com.alevel.service.client.ClientService;
import ua.com.alevel.service.mail.EmailParameters;
import ua.com.alevel.service.mail.EmailService;
import ua.com.alevel.service.trip.v2.TripServiceV2;
import ua.com.alevel.util.PriceAndDateUtil;
import ua.com.alevel.web.dto.BookingRequestDto;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final ClientService clientService;
    private final BookingRepository bookingRepository;
    private final TripServiceV2 tripServiceV2;
    private final EmailService emailService;

    @Transactional
    @Override
    public Booking createBooking(BookingRequestDto bookingRequestDto) {
        Booking booking = new Booking();
        booking.setUuid(UUID.randomUUID().toString());

        TripV2 trip = new TripV2();
        trip.setId(bookingRequestDto.getTripId());

        booking.setTrip(trip);
        booking.setStatus(BookingStatus.NEW);
        booking.setAdults(bookingRequestDto.getAdults());
        booking.setChildren(bookingRequestDto.getChildren());
        booking.setTotalPrice(bookingRequestDto.getTotalPrice());
        booking.setEmail(bookingRequestDto.getEmail());
        booking.setPhoneNumber(bookingRequestDto.getPhoneNumber());

        List<Passenger> passengers = bookingRequestDto.getPassengers().stream().map(preq -> {
            Passenger passenger = new Passenger();
            passenger.setBooking(booking);
            passenger.setFirstName(preq.getFirstName());
            passenger.setLastName(preq.getLastName());
            return passenger;
        }).collect(Collectors.toList());

        booking.setPassengers(passengers);
        booking.setUser(clientService.getCurrentClient());

        Booking savedBooking = bookingRepository.save(booking);

        tripServiceV2.plusUsedSeats(bookingRequestDto.getTripId(), bookingRequestDto.getPassengersCount());

        return savedBooking;
    }

    @Transactional
    @Override
    public Booking confirmBooking(Booking booking) {
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        return booking;
    }

    @Transactional
    @Override
    public Booking cancelBooking(Booking booking) {
        booking.setStatus(BookingStatus.CANCELED);

        bookingRepository.save(booking);

        //return used seats
        tripServiceV2.plusUsedSeats(booking.getTrip().getId(), -1 * (booking.getAdults() + booking.getChildren()));

        return booking;
    }

    @Override
    public Optional<Booking> findById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public Booking searchBooking(String uuid, String email) {
        return bookingRepository.getByUuidAndEmail(uuid, email);
    }

    @Async
    @Override
    public void sendBookingConfirmedNotification(Booking booking) {
        String text = """
                <h2>You received this message as confirmation about booking</h2>
                <p><b>Your Booking Number:<b> {0}</p> 
                <p><b>Departure:</b> {1} from {2}</p>
                <p><b>Arrival:</b> {3} to {4}</p>
                <p><b>Bus:</b> {5}</p>
                <p><b>Total Price:</b> {6} UAH</p>
                <p><b>Adults:</b> {7}</p>
                <p><b>Children:</b> {8}</p>
                <p><b>Passengers Info:</b> {9}</p>
                        """;

        String formatted = MessageFormat.format(text,
                booking.getUuid(),
                PriceAndDateUtil.formatLocalDateTime(booking.getTrip().getDeparture()), booking.getTrip().getRoute().getDepartureTown().getName(),
                PriceAndDateUtil.formatLocalDateTime(booking.getTrip().getArrival()), booking.getTrip().getRoute().getArrivalTown().getName(),
                booking.getTrip().getBus().getName(),
                booking.getTotalPrice(),
                booking.getAdults(),
                booking.getChildren(),
                booking.getPassengers().stream().map(Passenger::getFullName).collect(Collectors.joining(", "))
        );

        EmailParameters parameters = EmailParameters.builder().subject("Booking confirmation")
                .to(booking.getEmail())
                .message(formatted)
                .build();

        emailService.sendNotification(parameters);
    }

    @Async
    @Override
    public void sendBookingCanceledNotification(Booking booking) {
        String text = """
                <h2>You received this message as cancellation of your booking</h2>
                <p><b>Departure:</b> {0} from {1}</p>
                <p><b>Arrival:</b> {2} to {3}</p>
                <p><b>Total Price:</b> {4} UAH</p>
                <p><b>Adults:</b> {5}</p>
                <p><b>Children:</b> {6}</p>""";

        String formatted = MessageFormat.format(text,
                PriceAndDateUtil.formatLocalDateTime(booking.getTrip().getDeparture()), booking.getTrip().getRoute().getDepartureTown().getName(),
                PriceAndDateUtil.formatLocalDateTime(booking.getTrip().getArrival()), booking.getTrip().getRoute().getArrivalTown().getName(),
                booking.getTotalPrice(),
                booking.getAdults(),
                booking.getChildren()
        );

        EmailParameters parameters = EmailParameters.builder().subject("Booking cancellation")
                .to(booking.getEmail())
                .message(formatted)
                .build();

        emailService.sendNotification(parameters);
    }
}
