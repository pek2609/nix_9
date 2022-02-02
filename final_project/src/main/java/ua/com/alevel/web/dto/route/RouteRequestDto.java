package ua.com.alevel.web.dto.route;

import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.util.Messages;
import ua.com.alevel.validated.annotation.DifferentTowns;
import ua.com.alevel.web.dto.DtoRequest;

import javax.validation.constraints.NotNull;

@DifferentTowns
public class RouteRequestDto extends DtoRequest {

    @NotNull(message = Messages.NOT_NULL)
    private Town departureTown;

    @NotNull(message = Messages.NOT_NULL)
    private Town arrivalTown;

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
}
