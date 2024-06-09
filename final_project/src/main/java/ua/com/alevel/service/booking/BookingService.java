package ua.com.alevel.service.booking;

import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.web.dto.BookingRequestDto;

import java.util.Optional;

public interface BookingService {

    Booking createBooking(BookingRequestDto bookingRequestDto);

    Booking confirmBooking(Booking booking);
    Booking cancelBooking(Booking booking);

    Optional<Booking> findById(Long bookingId);

    Booking searchBooking(String uuid, String email);

    void sendBookingConfirmedNotification(Booking booking);

    void sendBookingCanceledNotification(Booking booking);
}

