package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.listener.ActivePromotionGenerationListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "promotions")
@EntityListeners({ActivePromotionGenerationListener.class})
public class Promotion extends BaseEntity {

    private String image;

    private String name;

    private Integer percent;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    @OneToMany(mappedBy = "promotion", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Trip> trips;

    @Transient
    private Boolean active;

    public Promotion() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> routes) {
        this.trips = routes;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
