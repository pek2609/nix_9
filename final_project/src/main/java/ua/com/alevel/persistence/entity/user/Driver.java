package ua.com.alevel.persistence.entity.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.type.Role;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "DRIVER")
@EqualsAndHashCode(callSuper = true)
public class Driver extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Driver() {
        super();
        setRole(Role.ROLE_DRIVER);
    }

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "drivers")
    private Set<TripV2> trips;

    public String getFullName() {
        return firstName + ' ' +  lastName;
    }
}
