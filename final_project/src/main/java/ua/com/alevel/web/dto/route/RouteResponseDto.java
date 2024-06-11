package ua.com.alevel.web.dto.route;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.RouteV2;
import ua.com.alevel.persistence.entity.Town;
import ua.com.alevel.web.dto.DtoResponse;


@Getter
@Setter
public class RouteResponseDto extends DtoResponse {

    private String imagePath;
    private Town departureTown;
    private Town arrivalTown;
    private String description;

    public RouteResponseDto(RouteV2 route) {
        super(route.getId(), route.getCreated(), route.getUpdated(), true);
        this.departureTown = route.getDepartureTown();
        this.arrivalTown = route.getArrivalTown();
        this.imagePath = route.getImagePath();
        this.description = route.getDescription();
    }
}
