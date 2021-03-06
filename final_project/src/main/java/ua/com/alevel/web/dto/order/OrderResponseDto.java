package ua.com.alevel.web.dto.order;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.web.dto.DtoResponse;

public class OrderResponseDto extends DtoResponse {

    private Trip trip;
    private String name;
    private String surname;
    private String phoneNumber;
    private Client client;
    private Integer children;
    private Integer adults;
    private Double check;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public OrderResponseDto(Order order) {
        super(order.getId(), order.getCreated(), order.getUpdated(), true);
        this.name = order.getFirstName();
        this.surname = order.getLastName();
        this.children = order.getChildrenNumber();
        this.adults = order.getAdultsNumber();
        this.client = order.getClient();
        this.trip = order.getTrip();
        this.check = order.getFinalPrice();
        this.phoneNumber = order.getPhoneNumber();
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Double getCheck() {
        return check;
    }

    public void setCheck(Double check) {
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }
}
