package ua.com.alevel.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "bus")
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
}
