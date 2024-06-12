package ua.com.alevel.service.booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.filter.BookingFilter;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.persistence.entity.RouteV2;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.web.dto.BookingRequestDto;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    Booking createBooking(BookingRequestDto bookingRequestDto);
    Booking confirmBooking(Booking booking);
    Booking cancelBooking(Booking booking);

    Optional<Booking> findById(Long bookingId);

    Booking searchBooking(String uuid, String email);

    Page<Booking> findAllByFilter(BookingFilter bookingFilter, Pageable pageable);

    List<Booking> getBookingsByTrip(Long tripId, String searchPhone);

    void sendBookingConfirmedNotification(Booking booking);

    void sendBookingCanceledNotification(Booking booking);
}

