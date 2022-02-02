package ua.com.alevel.web.dto.route;

import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.web.dto.DtoResponse;

public class RouteResponseDto extends DtoResponse {

    private Town departureTown;
    private Town arrivalTown;
    private String route;
    private Integer tripCount;


    public RouteResponseDto(Route route) {
        super(route.getId(), route.getCreated(), route.getUpdated(), true);
        this.departureTown = route.getDepartureTown();
        this.arrivalTown = route.getArrivalTown();
        this.route = route.getDepartureTown().name()+"-"+route.getArrivalTown().name();
        this.tripCount = route.getTrips().size();
    }

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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Integer getTripCount() {
        return tripCount;
    }

    public void setTripCount(Integer tripCount) {
        this.tripCount = tripCount;
    }
}
