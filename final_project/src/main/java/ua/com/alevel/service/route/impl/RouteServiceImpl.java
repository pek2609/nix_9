package ua.com.alevel.service.route.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.AlreadyExistEntity;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.RouteV2;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.repository.RouteRepository;
import ua.com.alevel.service.route.RouteService;
import ua.com.alevel.util.Messages;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final LoggerService loggerService;
    private final RouteRepository routeRepository;
    private final CrudRepositoryHelper<RouteV2, RouteRepository> crudRepositoryHelper;

    @Transactional
    @Override
    public void create(RouteV2 entity) {
        //get arrival and departure town
        if (routeRepository.existsByDepartureTownAndArrivalTownAndIdNot(entity.getDepartureTown(), entity.getArrivalTown(), entity.getId())) {
            loggerService.commit(LoggerLevel.ERROR, "route with this towns is already exist, " + entity.getDepartureTown().getName() + " " + entity.getArrivalTown().getName());
            throw new AlreadyExistEntity("route with this towns is already exist");
        }
        crudRepositoryHelper.create(routeRepository, entity);
    }

    @Transactional
    @Override
    public void update(RouteV2 entity) {
        RouteV2 existing = routeRepository.getById(entity.getId());
        if (entity.getImagePath() == null) {
            entity.setImagePath(existing.getImagePath());
        }
        if (routeRepository.existsByDepartureTownAndArrivalTownAndIdNot(entity.getDepartureTown(), entity.getArrivalTown(), entity.getId())) {
            loggerService.commit(LoggerLevel.ERROR, "route with this townss is already exist, " + entity.getDepartureTown().getName() + " " + entity.getArrivalTown().getName());
            throw new AlreadyExistEntity("route with this towns is already exist");
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
    public RouteV2 findById(Long id) {
        Optional<RouteV2> route = crudRepositoryHelper.findById(routeRepository, id);
        if (route.isEmpty()) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("route", id));
            throw new EntityNotFoundException("route is not found");
        }
        return route.get();
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<RouteV2> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(routeRepository, request);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RouteV2> findAll() {
        return routeRepository.findAll();
    }

    @Deprecated
    @Transactional(readOnly = true)
    @Override
    public List<RouteV2> findAllByTripsGroupsByRoute(List<Trip> trips) {
        return routeRepository.findAllByTripsGroupsByRoute(trips);
    }
}
