package ua.com.alevel.service.trip.v2;

import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.entity.user.Driver;
import ua.com.alevel.service.BaseService;
import ua.com.alevel.web.dto.trip.TripSearchRequest;
import ua.com.alevel.web.dto.trip.TripStatisticsDto;

import java.util.List;

public interface TripServiceV2 extends BaseService<TripV2> {

    List<SearchTripResult> searchTrips(TripSearchRequest request);

    SearchTripResult prepareTripSearchResult(Long tripId, Integer adults, Integer children);

    List<TripV2> getDriverTrips(Driver driver);
    TripStatisticsDto getTripStatistics(Long tripId);

    void plusUsedSeats(Long tripId, Integer passengerCount);

    DataTableResponse<TripV2> findAll(DataTableRequest request);
}
