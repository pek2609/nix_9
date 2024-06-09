package ua.com.alevel.web.dto.client;

import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.type.Sex;
import ua.com.alevel.web.dto.DtoResponse;

import java.util.Date;

public class ClientResponseDto extends DtoResponse {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Sex sex;
    private Date birthDate;
    private String fullName;
    private int orderCount;

    public ClientResponseDto(Client client) {
        super(client.getId(), client.getCreated(), client.getUpdated(), client.getEnabled());
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.phoneNumber = client.getPhoneNumber();
        this.birthDate = client.getBirthDate();
        this.email = client.getEmail();
        this.sex = client.getSex();
        this.fullName = client.getFullName();
        this.orderCount = 0;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}
