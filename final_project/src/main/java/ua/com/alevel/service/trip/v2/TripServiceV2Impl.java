package ua.com.alevel.service.trip.v2;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.repository.TripRepositoryV2;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TripServiceV2Impl implements TripServiceV2 {

    private final TripRepositoryV2 tripRepositoryV2;

    @Override
    public List<SearchTripResult> searchTrips(TripSearchRequest request) {

        List<TripV2> bySearch = tripRepositoryV2.findBySearch(request.getDepartureTownId(), request.getArrivalTownId(), Date.valueOf(request.getDepartureDate()), request.getAdults() + request.getChildren());

        return bySearch.stream().map(t -> SearchTripResult.from(t, request.getAdults(), request.getChildren()))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public SearchTripResult prepareTripSearchResult(Long tripId, Integer adults, Integer children) {
        return SearchTripResult.from(findById(tripId), adults, children);
    }

    @Transactional
    @Override
    public void plusUsedSeats(Long tripId, Integer passengerCount) {
        tripRepositoryV2.plusUsedSeats(tripId, passengerCount);
    }


    @Override
    public void create(TripV2 entity) {

    }

    @Override
    public void update(TripV2 entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Transactional
    @Override
    public TripV2 findById(Long id) {
        return tripRepositoryV2.findById(id).orElse(null);
    }

    @Override
    public DataTableResponse<TripV2> findAll(DataTableRequest request) {
        return null;
    }
}
