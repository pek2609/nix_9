package ua.com.alevel.service.route.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.repository.RouteRepository;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.service.route.RouteService;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final CrudRepositoryHelper<Route, RouteRepository> crudRepositoryHelper;

    public RouteServiceImpl(RouteRepository routeRepository, CrudRepositoryHelper<Route, RouteRepository> crudRepositoryHelper) {
        this.routeRepository = routeRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Transactional
    @Override
    public void create(Route entity) {
        crudRepositoryHelper.create(routeRepository, entity);
    }

    @Transactional
    @Override
    public void update(Route entity) {
        crudRepositoryHelper.update(routeRepository, entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(routeRepository, id);
    }

    @Transactional
    @Override
    public Route findById(Long id) {
        Optional<Route> route = crudRepositoryHelper.findById(routeRepository, id);
        if (route.isEmpty()) {
            throw new EntityNotFoundException("route is not found");
        }
        return route.get();
    }

    @Transactional
    @Override
    public DataTableResponse<Route> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(routeRepository, request);
    }

    @Transactional
    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Transactional
    public boolean existsByDepartureTownAndArrivalTown(Town departureTown, Town arrivalTown) {
        return routeRepository.existsByDepartureTownAndArrivalTown(departureTown, arrivalTown);
    }

    @Override
    public List<Route> findAllByTripsGroupsByRoute(List<Trip> trips) {
        return routeRepository.findAllByTripsGroupsByRoute(trips);
    }
}
