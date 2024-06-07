package ua.com.alevel.service.trip.v2;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.entity.Town;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Builder
@Data
@Getter
@Setter
public class SearchTripResult {
    private Town departureTown;
    private Town arrivalTown;

    private LocalDateTime departure;
    private LocalDateTime arrival;
    private Double totalPrice;
    private Double pricePerPerson;
    private Integer remainingPlaces;
    private Duration tripDuration;
    private Bus bus;

    public String getDepartureDateTimeFormatted() {
        return departure.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
    }

    public String getArrivalDateTimeFormatted() {
        return arrival.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
    }

    public String getTripDurationFormatted() {
        return tripDuration.toHours() + "h" + " " + tripDuration.toMinutesPart() + "m";
    }
}
