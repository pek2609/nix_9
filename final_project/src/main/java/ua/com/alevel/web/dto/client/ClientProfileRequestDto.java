package ua.com.alevel.web.dto.client;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.persistence.type.Sex;
import ua.com.alevel.util.Messages;
import ua.com.alevel.validated.annotation.ValidBirthDate;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Getter
@Setter
public class ClientProfileRequestDto extends DtoRequest {

    @NotBlank(message = Messages.NOT_NULL)
    private String firstName;

    @NotBlank(message = Messages.NOT_NULL)
    private String lastName;

    @Pattern(regexp = "^\\+?3?8?(0\\d{9})$", message = Messages.INVALID_NUMBER)
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidBirthDate
    private Date birthDate;
}
