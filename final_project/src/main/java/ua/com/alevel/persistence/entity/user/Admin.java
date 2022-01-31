package ua.com.alevel.persistence.entity.user;

import ua.com.alevel.persistence.type.Role;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ADMIN")
public class Admin extends User{

    public Admin() {
        super();
        setRole(Role.ROLE_ADMIN);
    }
}
