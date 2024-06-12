package ua.com.alevel.web.dto;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.persistence.entity.BookingStatus;
import ua.com.alevel.web.dto.trip.TripResponseDto;

@Getter
@Setter
public class BookingAdminResponseDto extends DtoResponse {

    private String uuid;
    private BookingStatus status;
    private TripResponseDto trip;
    private String email;
    private String phoneNumber;
    private Integer children;
    private Integer adults;
    private Double totalPrice;
    private String passengersInfo;

    public BookingAdminResponseDto(Booking booking) {
        super(booking.getId(), booking.getCreated(), booking.getUpdated(), false);
        status = booking.getStatus();
        uuid = booking.getUuid();
        trip = new TripResponseDto(booking.getTrip());
        email = booking.getEmail();
        phoneNumber = booking.getPhoneNumber();
        adults = booking.getAdults();
        children = booking.getChildren();
        totalPrice = booking.getTotalPrice();
    }
}
