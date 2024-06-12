package ua.com.alevel.service.trip.v2;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.TripStatus;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.entity.user.Driver;
import ua.com.alevel.persistence.repository.TripRepositoryV2;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TripServiceV2Impl implements TripServiceV2 {

    private final TripRepositoryV2 tripRepository;
    private CrudRepositoryHelper<TripV2, TripRepositoryV2> crudRepositoryHelper;

    @Override
    public List<SearchTripResult> searchTrips(TripSearchRequest request) {

        List<TripV2> bySearch = tripRepository.findBySearch(request.getDepartureTownId(), request.getArrivalTownId(), Date.valueOf(request.getDepartureDate()), request.getAdults() + request.getChildren());

        return bySearch.stream().filter(t -> t.getTripStatus() == TripStatus.PLANNED).map(t -> SearchTripResult.from(t, request.getAdults(), request.getChildren()))
                .collect(Collectors.toList());
    }

    @Override
    public SearchTripResult prepareTripSearchResult(Long tripId, Integer adults, Integer children) {
        return SearchTripResult.from(findById(tripId), adults, children);
    }

    @Override
    public List<TripV2> getDriverTrips(Driver driver) {
        if (driver == null) {
            return Collections.emptyList();
        }
        return tripRepository.getTripV2ByDriversContains(driver).stream()
                .filter(tripV2 -> tripV2.getTripStatus() != TripStatus.COMPLETED)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void plusUsedSeats(Long tripId, Integer passengerCount) {
        tripRepository.plusUsedSeats(tripId, passengerCount);
    }

    @Override
    public DataTableResponse<TripV2> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(tripRepository, request);
    }

    @Transactional
    @Override
    public void create(TripV2 entity) {
        crudRepositoryHelper.create(tripRepository, entity);
    }

    @Transactional
    @Override
    public void update(TripV2 entity) {
        //validate check bookings
        crudRepositoryHelper.update(tripRepository, entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        //validate check bookings

        crudRepositoryHelper.delete(tripRepository, id);
    }

    @Override
    public TripV2 findById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }
}
