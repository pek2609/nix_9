package ua.com.alevel.persistence.entity.user;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.listener.FullNameGenerationListener;
import ua.com.alevel.persistence.type.Role;
import ua.com.alevel.persistence.type.Sex;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = "CLIENT")
@EntityListeners({FullNameGenerationListener.class})
public class Client extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;

    @Transient
    private String fullName;

    public Client() {
        super();
        setRole(Role.ROLE_CLIENT);
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
