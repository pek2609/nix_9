package ua.com.alevel.web.dto.order;

import org.hibernate.validator.constraints.Range;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.util.Messages;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OrderRequestDto extends DtoRequest {

    @NotNull(message = Messages.NOT_NULL)
    private Long tripId;

    @NotNull(message = Messages.NOT_NULL)
    private String name;

    @NotNull(message = Messages.NOT_NULL)
    private String surname;

    @Pattern(regexp = "^\\+?3?8?(0\\d{9})$", message = Messages.INVALID_NUMBER)
    private String phoneNumber;

    private Client client;

    @Range(min = 0, max = 5, message = Messages.INVALID_ADULTS_CHILDREN_NUMBER)
    private Integer children;

    @Range(min = 0, max = 5, message = Messages.INVALID_ADULTS_CHILDREN_NUMBER)
    private Integer adults;

    private Double check;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getTrip() {
        return tripId;
    }

    public void setTrip(Long trip) {
        this.tripId = trip;
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
