package ua.com.alevel.service.trip.v2;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.repository.TripRepositoryV2;
import ua.com.alevel.util.PriceAndDateUtil;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import java.sql.Date;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TripServiceV2Impl implements TripServiceV2 {

    private final TripRepositoryV2 tripRepositoryV2;

    @Override
    public List<SearchTripResult> searchTrips(TripSearchRequest request) {

        List<TripV2> bySearch = tripRepositoryV2.findBySearch(request.getDepartureTownId(), request.getArrivalTownId(), Date.valueOf(request.getDepartureDate()), request.getAdults() + request.getChildren());

        return bySearch.stream().map(t -> toResult(t, request))
                .collect(Collectors.toList());

    }

    private SearchTripResult toResult(TripV2 tripV2, TripSearchRequest request) {
        return SearchTripResult.builder()
                .departureTown(tripV2.getRoute().getDepartureTown())
                .arrivalTown(tripV2.getRoute().getArrivalTown())
                .pricePerPerson(tripV2.getPrice())
                .remainingPlaces(tripV2.getBus().getSeats() - tripV2.getUsedSeats())
                .tripDuration(Duration.between(tripV2.getDeparture(), tripV2.getArrival()))
                .totalPrice(PriceAndDateUtil.countPrice(request.getAdults(), request.getChildren(), tripV2.getPrice()))
                .departure(tripV2.getDeparture())
                .arrival(tripV2.getArrival())
                .bus(tripV2.getBus())
                .build();
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

    @Override
    public TripV2 findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<TripV2> findAll(DataTableRequest request) {
        return null;
    }
}
