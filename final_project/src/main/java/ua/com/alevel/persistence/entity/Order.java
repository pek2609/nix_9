package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.entity.user.Client;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "last_name" ,nullable = false)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "children_number")
    private int childrenNumber;

    @Column(name = "adults_number")
    private int adultsNumber;

    @Column(name = "final_price", precision = 10, scale = 2)
    private Double finalPrice;

    @ManyToOne
    @JoinColumn(name = "trip_id", updatable = false)
    private Trip trip;

    public Order() {
        super();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surname) {
        this.lastName = surname;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(int children) {
        this.childrenNumber = children;
    }

    public int getAdultsNumber() {
        return adultsNumber;
    }

    public void setAdultsNumber(int adults) {
        this.adultsNumber = adults;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double check) {
        this.finalPrice = check;
    }
}
