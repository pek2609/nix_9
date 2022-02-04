package ua.com.alevel.persistence.listener;

import ua.com.alevel.persistence.entity.Trip;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.Date;

public class VisibleTripGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateTripVisible(Trip trip) {
        Date cur = new Date();
        boolean visible = cur.compareTo(trip.getDeparture()) < 0 || trip.getLeftSeats() == 0;
        trip.setVisible(visible);
    }
}
