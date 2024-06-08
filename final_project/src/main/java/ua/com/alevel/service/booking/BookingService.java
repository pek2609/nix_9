package ua.com.alevel.service.booking;

import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.web.dto.BookingRequestDto;

public interface BookingService {

    Booking createBooking(BookingRequestDto bookingRequestDto);
}
