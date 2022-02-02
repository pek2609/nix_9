package ua.com.alevel.web.dto.client;

import org.hibernate.validator.constraints.Length;
import ua.com.alevel.util.Messages;
import ua.com.alevel.validated.annotation.PasswordMatches;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@PasswordMatches
public class ChangePasswordRequestDto extends DtoRequest {

    @NotBlank(message = Messages.NOT_NULL)
    @Email(message = Messages.INVALID_EMAIL)
    private String email;

    @Length(min = 8, max = 32, message = "at least 8 characters, max 32")
    @NotBlank(message = Messages.NOT_NULL)
    private String oldPassword;

    @Length(min = 8, max = 32, message = "at least 8 characters, max 32")
    @NotBlank(message = Messages.NOT_NULL)
    private String password;

    private String passwordConfirm;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
