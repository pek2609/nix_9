package ua.com.alevel.facade.trip;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.trip.TripRequestDto;
import ua.com.alevel.web.dto.trip.TripResponseDto;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import java.util.List;

public interface TripFacade extends BaseFacade<TripRequestDto, TripResponseDto> {

    PageData<TripResponseDto> findAllByBus(WebRequest request, Long busId);
    PageData<TripResponseDto> findAllByPromotion(WebRequest request, Long promotionId);
    PageData<TripResponseDto> findAllByRoute(WebRequest request, Long routeId);
    List<TripResponseDto> findAllBySearch(TripSearchRequest tripSearchRequest);
}
