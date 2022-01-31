package ua.com.alevel.service.route;

import org.springframework.data.repository.query.Param;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.service.BaseService;

import java.util.List;

public interface RouteService extends BaseService<Route> {

    List<Route> findAll();

    boolean existsByDepartureTownAndArrivalTown(Town departureTown, Town arrivalTown);

    List<Route> findAllByTripsGroupsByRoute(List<Trip> trips);
}
