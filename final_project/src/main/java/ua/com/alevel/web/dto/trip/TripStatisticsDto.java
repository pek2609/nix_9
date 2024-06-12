package ua.com.alevel.web.dto.trip;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.Passenger;

import java.util.List;

@Builder
@Getter
@Setter
public class TripStatisticsDto {

    private TripResponseDto tripResponseDto;
    private List<Passenger> passengerList;


    //BOOKING STATISTICS
    private int bookingCount;
    private int confirmedBookingCount;
    private int newBookingCount;
    private int canceledBookingCount;
    private double confirmedBookingPercent;
    private double newBookingPercent;
    private double canceledBookingPercent;


    //PASSENGER STATISTICS
    private int passengersCount;
    private int adultsCount;
    private double adultsPercent;
    private int childrenCount;
    private double childrenPercent;

    private int presentCount;
    private int nonPresentCount;
    private int nonMarkedPresentCount;

    private double presentPercent;
    private double nonPresentPercent;
    private double nonMarkedPresentPercent;

}
