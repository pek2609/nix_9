package ua.com.alevel.web.dto.trip;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.entity.RouteV2;
import ua.com.alevel.persistence.entity.TripStatus;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.entity.user.Driver;
import ua.com.alevel.util.PriceAndDateUtil;
import ua.com.alevel.web.dto.DtoResponse;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
public class TripResponseDto extends DtoResponse {

    private Bus bus;
    private RouteV2 route;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private Double price;
    private TripStatus status;
    private int usedSeats;
    private Set<Driver> drivers;

    public TripResponseDto(TripV2 trip) {
        super(trip.getId(), trip.getCreated(), trip.getUpdated(), true);
        this.bus = trip.getBus();
        this.route = trip.getRoute();
        this.departure = trip.getDeparture();
        this.arrival = trip.getArrival();
        this.price = trip.getPrice();
        this.usedSeats = trip.getUsedSeats();
        this.status = trip.getTripStatus();
    }

    public String getDriversJoinName() {
        if (CollectionUtils.isEmpty(drivers)) {
            return "-";
        }
        return drivers.stream().map(Driver::getFullName).collect(Collectors.joining(", "));
    }

    public String getTripShortInfo() {
        String routeName = this.getRoute().getDepartureTown().getName() + " " + this.getRoute().getArrivalTown().getName();
        return routeName + " " + PriceAndDateUtil.formatLocalDateTime(this.getDeparture());
    }
}
