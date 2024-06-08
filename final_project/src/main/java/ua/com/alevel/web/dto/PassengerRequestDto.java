package ua.com.alevel.web.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PassengerRequestDto extends DtoRequest {

    private String firstName;
    private String lastName;
}
