package ua.com.alevel.web.dto.trip;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.entity.RouteV2;
import ua.com.alevel.persistence.entity.user.Driver;
import ua.com.alevel.util.Messages;
import ua.com.alevel.validated.annotation.DepartureArrival;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@DepartureArrival
@Getter
@Setter
public class TripRequestDto extends DtoRequest {

    @NotNull(message = Messages.NOT_NULL)
    private Bus bus;

    @NotNull(message = Messages.NOT_NULL)
    private RouteV2 route;

    @NotNull(message = Messages.NOT_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime departure;

    @NotNull(message = Messages.NOT_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime arrival;

    private int usedSeats;

    private Set<Driver> drivers;

    @Min(0)
    private Double price;
}
