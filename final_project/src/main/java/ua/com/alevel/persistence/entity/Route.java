package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.Town;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "routs", uniqueConstraints = {@UniqueConstraint(columnNames = {"departure_town", "arrival_town"})})
public class Route extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "departure_town", nullable = false)
    private Town departureTown;

    @Enumerated(EnumType.STRING)
    @Column(name = "arrival_town" , nullable = false)
    private Town arrivalTown;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Trip> trips;

    public Route() {
        super();
    }

    public Town getDepartureTown() {
        return departureTown;
    }

    public void setDepartureTown(Town departureTown) {
        this.departureTown = departureTown;
    }

    public Town getArrivalTown() {
        return arrivalTown;
    }

    public void setArrivalTown(Town arrivalTown) {
        this.arrivalTown = arrivalTown;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
