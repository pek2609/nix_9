package ua.com.alevel.web.dto.trip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.util.Messages;
import ua.com.alevel.validated.annotation.ChildrenAdults;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@ToString
@ChildrenAdults
public class TripSearchRequest extends DtoRequest {

    @NotNull
    private Long departureTownId;
    @NotNull
    private Long arrivalTownId;

    @Deprecated
    private Town departureTown;

    @Deprecated
    private Town arrivalTown;

    @NotNull(message = Messages.NOT_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departure;

    @NotNull(message = Messages.NOT_NULL)
    private Integer children;

    @NotNull(message = Messages.NOT_NULL)
    private Integer adults;
}
