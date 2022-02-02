package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.listener.LeftSeatsCountListener;
import ua.com.alevel.persistence.listener.VisibleTripGenerationListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trips")
@EntityListeners({LeftSeatsCountListener.class, VisibleTripGenerationListener.class})
public class Trip extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date departure;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date arrival;

    @Column(precision = 7, scale = 2)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Order> orders;

    @Transient
    private Boolean visible;

    @Transient
    private Integer leftSeats;

    public Trip() {
        super();
    }

    public Integer getLeftSeats() {
        return leftSeats;
    }

    public void setLeftSeats(Integer leftSeats) {
        this.leftSeats = leftSeats;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
}
