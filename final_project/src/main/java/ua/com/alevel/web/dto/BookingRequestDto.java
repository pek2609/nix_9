package ua.com.alevel.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.service.trip.v2.SearchTripResult;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
public class BookingRequestDto extends DtoRequest {

    private List<PassengerRequestDto> passengers;
    private String email;
    private String phoneNumber;
    private Long tripId;
    private Double totalPrice;

    @NotNull
    private int adults;

    @NotNull
    private int children;


    public int getPassengersCount() {
        return adults + children;
    }
}
