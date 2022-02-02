package ua.com.alevel.web.dto.trip;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.util.Messages;
import ua.com.alevel.validated.annotation.DepartureArrival;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@DepartureArrival
public class TripRequestDto extends DtoRequest {

    @NotNull(message = Messages.NOT_NULL)
    private Bus bus;

    @NotNull(message = Messages.NOT_NULL)
    private Route route;

    @NotNull(message = Messages.NOT_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date departure;

    @NotNull(message = Messages.NOT_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date arrival;

    @Min(0)
    private Double price;

    private Promotion promotion;

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
