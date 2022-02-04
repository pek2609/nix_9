package ua.com.alevel.service.trip;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.service.BaseService;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import java.util.List;

public interface TripService extends BaseService<Trip> {

    DataTableResponse<Trip> findAllByBus(DataTableRequest request, Long busId);

    DataTableResponse<Trip> findAllByPromotion(DataTableRequest request, Long promotionId);

    DataTableResponse<Trip> findAllByRoute(DataTableRequest request, Long routeId);

    List<Trip> findAllBySearch(TripSearchRequest tripSearchRequest);
}
