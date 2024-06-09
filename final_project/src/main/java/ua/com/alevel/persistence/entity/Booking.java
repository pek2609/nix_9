package ua.com.alevel.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "booking")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Data
public class Booking extends BaseEntity {

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phoneNumber;

    @Column(name = "adults", nullable = false)
    private Integer adults;

    @Column(name = "children", nullable = false)
    private Integer children;

    @Column(name = "price", nullable = false)
    private Double totalPrice;

    @Column(name = "status", nullable = false)
    private BookingStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private TripV2 trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Passenger> passengers;
}
