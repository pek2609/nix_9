package ua.com.alevel.facade.route.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.AlreadyExistEntity;
import ua.com.alevel.facade.route.RouteFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.service.route.RouteService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.web.dto.datatable.PageAndSizeData;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.datatable.SortData;
import ua.com.alevel.web.dto.route.RouteRequestDto;
import ua.com.alevel.web.dto.route.RouteResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteFacadeImpl implements RouteFacade {

    private final RouteService routeService;

    public RouteFacadeImpl(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public void create(RouteRequestDto routeRequestDto) {
        Route route = getRouteFromDto(routeRequestDto);
        if (routeService.existsByDepartureTownAndArrivalTown(routeRequestDto.getDepartureTown(), routeRequestDto.getArrivalTown())) {
            throw new AlreadyExistEntity("this root is already exist");
        }
        routeService.create(route);
    }

    @Override
    public void update(RouteRequestDto routeRequestDto, Long id) {
        Route route = getRouteFromDto(routeRequestDto);
        if (routeService.existsByDepartureTownAndArrivalTown(routeRequestDto.getDepartureTown(), routeRequestDto.getArrivalTown())) {
            throw new AlreadyExistEntity("this root is already exist");
        }
        route.setId(id);
        routeService.update(route);
    }

    @Override
    public void delete(Long id) {
        routeService.delete(id);
    }

    @Override
    public RouteResponseDto findById(Long id) {
        return new RouteResponseDto(routeService.findById(id));
    }

    @Override
    public PageData<RouteResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<Route> all = routeService.findAll(dataTableRequest);
        List<RouteResponseDto> buses = all.getItems()
                .stream()
                .map(RouteResponseDto::new)
                .collect(Collectors.toList());

        PageData<RouteResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(buses, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<RouteResponseDto> findAll() {
        return routeService.findAll().stream().map(RouteResponseDto::new).toList();
    }

    private Route getRouteFromDto(RouteRequestDto routeRequestDto) {
        Route route = new Route();
        route.setArrivalTown(routeRequestDto.getArrivalTown());
        route.setDepartureTown(routeRequestDto.getDepartureTown());
        return route;
    }
}
