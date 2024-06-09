package ua.com.alevel.persistence.entity.user;

import ua.com.alevel.persistence.type.Role;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DRIVER")
public class Driver extends User {

    public Driver() {
        super();
        setRole(Role.ROLE_DRIVER);
    }
}
