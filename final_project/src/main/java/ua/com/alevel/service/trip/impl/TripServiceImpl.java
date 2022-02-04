package ua.com.alevel.service.trip.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.repository.TripRepository;
import ua.com.alevel.service.trip.TripService;
import ua.com.alevel.util.DataTableUtil;
import ua.com.alevel.util.Messages;
import ua.com.alevel.util.PriceAndDateUtil;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {


    private final LoggerService loggerService;
    private final TripRepository tripRepository;
    private final CrudRepositoryHelper<Trip, TripRepository> crudRepositoryHelper;

    public TripServiceImpl(LoggerService loggerService, TripRepository tripRepository, CrudRepositoryHelper<Trip, TripRepository> crudRepositoryHelper) {
        this.loggerService = loggerService;
        this.tripRepository = tripRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Transactional
    @Override
    public void create(Trip entity) {
        crudRepositoryHelper.create(tripRepository, entity);
    }

    @Transactional
    @Override
    public void update(Trip entity) {
        crudRepositoryHelper.update(tripRepository, entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "trip", id, "start"));
        crudRepositoryHelper.delete(tripRepository, id);
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "trip", id, "start"));
    }

    @Transactional
    @Override
    public Trip findById(Long id) {
        Optional<Trip> trip = crudRepositoryHelper.findById(tripRepository, id);
        if (trip.isEmpty()) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("trip", id));
            throw new EntityNotFoundException("trip is not found");
        }
        return trip.get();
    }

    @Transactional
    @Override
    public DataTableResponse<Trip> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(tripRepository, request);
    }

    @Transactional
    @Override
    public DataTableResponse<Trip> findAllByBus(DataTableRequest request, Long busId) {
        Page<Trip> tripPage = tripRepository.findByBusId(busId, DataTableUtil.formPageableByRequest(request));
        return DataTableUtil.formResponse(tripPage, request);
    }

    @Transactional
    @Override
    public DataTableResponse<Trip> findAllByPromotion(DataTableRequest request, Long promotionId) {
        Page<Trip> tripPage = tripRepository.findByPromotionId(promotionId, DataTableUtil.formPageableByRequest(request));
        return DataTableUtil.formResponse(tripPage, request);
    }

    @Transactional
    @Override
    public DataTableResponse<Trip> findAllByRoute(DataTableRequest request, Long routeId) {
        Page<Trip> tripPage = tripRepository.findByRouteId(routeId, DataTableUtil.formPageableByRequest(request));
        return DataTableUtil.formResponse(tripPage, request);
    }

    @Override
    public List<Trip> findAllBySearch(TripSearchRequest tripSearchRequest) {
        Date arrival = PriceAndDateUtil.addOneDay(tripSearchRequest.getDeparture());
        List<Trip> search = tripRepository.findBySearch(tripSearchRequest.getDepartureTown(), tripSearchRequest.getArrivalTown(), tripSearchRequest.getDeparture(), arrival);
        search = search.stream().filter(trip -> trip.getVisible() && (trip.getLeftSeats() >= tripSearchRequest.getChildren() + tripSearchRequest.getAdults())).toList();
        return search;
    }
}
