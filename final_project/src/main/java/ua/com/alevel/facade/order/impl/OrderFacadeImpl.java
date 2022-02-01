package ua.com.alevel.facade.order.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.service.order.OrderService;
import ua.com.alevel.service.trip.TripService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.web.dto.datatable.PageAndSizeData;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.datatable.SortData;
import ua.com.alevel.web.dto.order.OrderRequestDto;
import ua.com.alevel.web.dto.order.OrderResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final TripService tripService;

    public OrderFacadeImpl(OrderService orderService, TripService tripService) {
        this.orderService = orderService;
        this.tripService = tripService;
    }

    @Override
    public void create(OrderRequestDto orderRequestDto) {
        Order order = getOrderFromRequestDto(orderRequestDto);
        order.setTrip(tripService.findById(orderRequestDto.getTrip()));
        orderService.create(order);
    }

    @Override
    public void update(OrderRequestDto orderRequestDto, Long id) {
        Order order = getOrderFromRequestDto(orderRequestDto);
        order.setId(id);
        orderService.update(order);
    }

    @Override
    public void delete(Long id) {
        orderService.delete(id);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        return new OrderResponseDto(orderService.findById(id));
    }

    @Override
    public PageData<OrderResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<Order> all = orderService.findAll(dataTableRequest);
        List<OrderResponseDto> clients = all.getItems()
                .stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());

        PageData<OrderResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(clients, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private Order getOrderFromRequestDto(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setPhoneNumber(orderRequestDto.getPhoneNumber());
        order.setAdultsNumber(orderRequestDto.getAdults());
        order.setChildrenNumber(orderRequestDto.getChildren());
        order.setFirstName(orderRequestDto.getName());
        order.setLastName(orderRequestDto.getSurname());
        order.setFinalPrice(orderRequestDto.getCheck());
        order.setClient(orderRequestDto.getClient());
        return order;
    }
}
