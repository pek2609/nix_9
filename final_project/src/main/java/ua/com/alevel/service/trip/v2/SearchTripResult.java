package ua.com.alevel.service.trip.v2;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.entity.Town;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.util.PriceAndDateUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Builder
@Data
@Getter
@Setter
@ToString
public class SearchTripResult {
    private Long tripId;
    private Town departureTown;
    private Town arrivalTown;

    private LocalDateTime departure;
    private LocalDateTime arrival;
    private Double totalPrice;
    private Double pricePerPerson;
    private Integer remainingPlaces;
    private Duration tripDuration;
    private Bus bus;

    public static SearchTripResult from(TripV2 tripV2, Integer adults, Integer children) {
        return SearchTripResult.builder()
                .departureTown(tripV2.getRoute().getDepartureTown())
                .arrivalTown(tripV2.getRoute().getArrivalTown())
                .pricePerPerson(tripV2.getPrice())
                .remainingPlaces(tripV2.getBus().getSeats() - tripV2.getUsedSeats())
                .tripDuration(Duration.between(tripV2.getDeparture(), tripV2.getArrival()))
                .totalPrice(PriceAndDateUtil.countPrice(adults, children, tripV2.getPrice()))
                .departure(tripV2.getDeparture())
                .arrival(tripV2.getArrival())
                .bus(tripV2.getBus())
                .tripId(tripV2.getId())
                .build();
    }

    public String getDepartureDateTimeFormatted() {
        return PriceAndDateUtil.formatLocalDateTime(departure);
    }

    public String getArrivalDateTimeFormatted() {
        return PriceAndDateUtil.formatLocalDateTime(arrival);

    }

    public String getTripDurationFormatted() {
        return tripDuration.toHours() + "h" + " " + tripDuration.toMinutesPart() + "m";
    }
}
