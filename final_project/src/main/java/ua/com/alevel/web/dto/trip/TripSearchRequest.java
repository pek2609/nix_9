package ua.com.alevel.web.dto.trip;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.util.Messages;
import ua.com.alevel.validated.ChildrenAdults;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ChildrenAdults
public class TripSearchRequest extends DtoRequest {

    @NotNull(message = Messages.NOT_NULL)
    private Town departureTown;

    @NotNull(message = Messages.NOT_NULL)
    private Town arrivalTown;

    @NotNull(message = Messages.NOT_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departure;

    @NotNull(message = Messages.NOT_NULL)
    private Integer children;

    @NotNull(message = Messages.NOT_NULL)
    private Integer adults;

    public Town getDepartureTown() {
        return departureTown;
    }

    public void setDepartureTown(Town departureTown) {
        this.departureTown = departureTown;
    }

    public Town getArrivalTown() {
        return arrivalTown;
    }

    public void setArrivalTown(Town arrivalTown) {
        this.arrivalTown = arrivalTown;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
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
