package ua.com.alevel.facade.trip;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.persistence.entity.user.Driver;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.trip.TripRequestDto;
import ua.com.alevel.web.dto.trip.TripResponseDto;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import java.util.List;
import java.util.Set;

public interface TripFacade extends BaseFacade<TripRequestDto, TripResponseDto> {

    void patchDrivers(Long tripId, Set<Long> driverIds);
    @Deprecated
    PageData<TripResponseDto> findAllByBus(WebRequest request, Long busId);

    @Deprecated
    PageData<TripResponseDto> findAllByPromotion(WebRequest request, Long promotionId);

    @Deprecated
    PageData<TripResponseDto> findAllByRoute(WebRequest request, Long routeId);

    @Deprecated
    List<TripResponseDto> findAllBySearch(TripSearchRequest tripSearchRequest);
}
