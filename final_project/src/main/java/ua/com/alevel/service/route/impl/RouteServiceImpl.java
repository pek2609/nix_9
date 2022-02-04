package ua.com.alevel.service.route.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.AlreadyExistEntity;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.repository.RouteRepository;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.service.route.RouteService;
import ua.com.alevel.util.Messages;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private final LoggerService loggerService;
    private final RouteRepository routeRepository;
    private final CrudRepositoryHelper<Route, RouteRepository> crudRepositoryHelper;

    public RouteServiceImpl(LoggerService loggerService, RouteRepository routeRepository, CrudRepositoryHelper<Route, RouteRepository> crudRepositoryHelper) {
        this.loggerService = loggerService;
        this.routeRepository = routeRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Transactional
    @Override
    public void create(Route entity) {
        if (routeRepository.existsByDepartureTownAndArrivalTown(entity.getDepartureTown(), entity.getArrivalTown())) {
            loggerService.commit(LoggerLevel.ERROR, "route with this towns is already exist, " + entity.getDepartureTown().name() + " " + entity.getArrivalTown().name());
            throw new AlreadyExistEntity("route with this towns is already exist");
        }
        crudRepositoryHelper.create(routeRepository, entity);
    }

    @Transactional
    @Override
    public void update(Route entity) {
        if (routeRepository.existsByDepartureTownAndArrivalTown(entity.getDepartureTown(), entity.getArrivalTown())) {
            loggerService.commit(LoggerLevel.ERROR, "route with this towns is already exist, " + entity.getDepartureTown().name() + " " + entity.getArrivalTown().name());
            throw new AlreadyExistEntity("route with this town is already exist");
        }
        crudRepositoryHelper.update(routeRepository, entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "route", id, "start"));
        crudRepositoryHelper.delete(routeRepository, id);
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "route", id, "end"));
    }

    @Transactional(readOnly = true)
    @Override
    public Route findById(Long id) {
        Optional<Route> route = crudRepositoryHelper.findById(routeRepository, id);
        if (route.isEmpty()) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("route", id));
            throw new EntityNotFoundException("route is not found");
        }
        return route.get();
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<Route> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(routeRepository, request);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public boolean existsByDepartureTownAndArrivalTown(Town departureTown, Town arrivalTown) {
        return routeRepository.existsByDepartureTownAndArrivalTown(departureTown, arrivalTown);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Route> findAllByTripsGroupsByRoute(List<Trip> trips) {
        return routeRepository.findAllByTripsGroupsByRoute(trips);
    }
}
