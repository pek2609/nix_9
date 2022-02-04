package ua.com.alevel.web.dto.client;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.persistence.type.Sex;
import ua.com.alevel.util.Messages;
import ua.com.alevel.validated.annotation.PasswordMatches;
import ua.com.alevel.validated.annotation.ValidBirthDate;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@PasswordMatches
public class ClientRegisterRequestDto extends DtoRequest {

    @NotBlank(message = Messages.NOT_NULL)
    private String firstName;

    @NotBlank(message = Messages.NOT_NULL)
    private String lastName;

    @Pattern(regexp = "^\\+?3?8?(0\\d{9})$", message = Messages.INVALID_NUMBER)
    private String phoneNumber;

    @NotBlank(message = Messages.NOT_NULL)
    @Email(message = Messages.INVALID_EMAIL)
    private String email;

    @NotNull(message = Messages.INVALID_TYPE)
    private Sex sex;

    @Length(min = 8, max = 32, message = "at least 8 characters, max 32")
    @NotBlank(message = Messages.NOT_NULL)
    private String password;

    private String passwordConfirm;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidBirthDate
    private Date birthDate;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
