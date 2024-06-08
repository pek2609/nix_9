package ua.com.alevel.service.booking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.persistence.entity.BookingStatus;
import ua.com.alevel.persistence.entity.Passenger;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.repository.BookingRepository;
import ua.com.alevel.service.client.ClientService;
import ua.com.alevel.service.trip.v2.TripServiceV2;
import ua.com.alevel.web.dto.BookingRequestDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final ClientService clientService;
    private final BookingRepository bookingRepository;
    private final TripServiceV2 tripServiceV2;

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
}
