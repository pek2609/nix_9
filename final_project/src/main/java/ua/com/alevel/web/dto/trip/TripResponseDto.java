package ua.com.alevel.web.dto.trip;

import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.web.dto.DtoResponse;

import java.util.Date;

public class TripResponseDto extends DtoResponse {

    private Bus bus;
    private Route route;
    private Date departure;
    private Date arrival;
    private Double price;
    private Promotion promotion;
    private Integer orderCount;
    private Double finalPrice;
    private Integer leftSeats;

    public TripResponseDto(Trip trip) {
        super(trip.getId(), trip.getCreated(), trip.getUpdated(), trip.getVisible());
        this.bus = trip.getBus();
        this.route = trip.getRoute();
        this.promotion = trip.getPromotion();
        this.departure = trip.getDeparture();
        this.arrival = trip.getArrival();
        this.arrival = trip.getArrival();
        this.orderCount = trip.getOrders().size();
        this.price = trip.getPrice();
        this.finalPrice = trip.getFinalPrice();
        this.leftSeats = trip.getLeftSeats();
    }

    public Integer getLeftSeats() {
        return leftSeats;
    }

    public void setLeftSeats(Integer leftSeats) {
        this.leftSeats = leftSeats;
    }

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

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
