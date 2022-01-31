package ua.com.alevel.persistence.listener;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Trip;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.List;

public class LeftSeatsCountListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateFullName(Trip trip) {
        int seats = trip.getBus().getSeats();
        List<Order> orders = trip.getOrders();
        if (orders != null) {
            for (Order order : orders) {
                seats -= (order.getChildren() + order.getAdults());
            }
        }
        trip.setLeftSeats(seats);
    }
}
