package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buses")
public class Bus extends BaseEntity {

    @Column(name = "image")
    private String imageUrl;

    private String name;

    private Integer seats;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Trip> trips;

    public Bus() {
        super();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
