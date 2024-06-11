package ua.com.alevel.service.route;

import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.entity.RouteV2;
import ua.com.alevel.persistence.entity.Town;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.service.BaseService;

import java.util.List;

public interface RouteService extends BaseService<RouteV2> {

    List<RouteV2> findAll();

    @Deprecated
    List<RouteV2> findAllByTripsGroupsByRoute(List<Trip> trips);
}
