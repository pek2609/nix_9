package ua.com.alevel.facade.order;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.order.OrderRequestDto;
import ua.com.alevel.web.dto.order.OrderResponseDto;

@Deprecated
public interface OrderFacade extends BaseFacade<OrderRequestDto, OrderResponseDto> {

    PageData<OrderResponseDto> findByClient(Long clientId, WebRequest webRequest);

    PageData<OrderResponseDto> findByTrip(Long tripId, WebRequest webRequest);
}
