package ua.com.alevel.facade.trip.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.trip.TripFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.service.bus.BusService;
import ua.com.alevel.service.promotion.PromotionService;
import ua.com.alevel.service.route.RouteService;
import ua.com.alevel.service.trip.TripService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.web.dto.datatable.PageAndSizeData;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.datatable.SortData;
import ua.com.alevel.web.dto.trip.TripRequestDto;
import ua.com.alevel.web.dto.trip.TripResponseDto;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripFacadeImpl implements TripFacade {

    private final TripService tripService;
    private final BusService busService;
    private final RouteService routeService;
    private final PromotionService promotionService;

    public TripFacadeImpl(TripService tripService, BusService busService, RouteService routeService, PromotionService promotionService) {
        this.tripService = tripService;
        this.busService = busService;
        this.routeService = routeService;
        this.promotionService = promotionService;
    }

    @Override
    public void create(TripRequestDto tripRequestDto) {
        Trip trip = getTripFromDto(tripRequestDto);
        tripService.create(trip);
    }

    @Override
    public void update(TripRequestDto tripRequestDto, Long id) {
        Trip trip = getTripFromDto(tripRequestDto);
        trip.setId(id);
        tripService.create(trip);
    }

    @Override
    public void delete(Long id) {
        tripService.delete(id);
    }

    @Override
    public TripResponseDto findById(Long id) {
        return new TripResponseDto(tripService.findById(id));
    }

    @Override
    public PageData<TripResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<Trip> all = tripService.findAll(dataTableRequest);
        List<TripResponseDto> buses = mapItemsToDto(all);

        PageData<TripResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(buses, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<TripResponseDto> findAllByBus(WebRequest request, Long busId) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<Trip> all = tripService.findAllByBus(dataTableRequest, busId);
        List<TripResponseDto> buses = mapItemsToDto(all);

        PageData<TripResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(buses, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<TripResponseDto> findAllByPromotion(WebRequest request, Long promotionId) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<Trip> all = tripService.findAllByPromotion(dataTableRequest, promotionId);
        List<TripResponseDto> buses = mapItemsToDto(all);

        PageData<TripResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(buses, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<TripResponseDto> findAllByRoute(WebRequest request, Long routeId) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<Trip> all = tripService.findAllByRoute(dataTableRequest, routeId);
        List<TripResponseDto> buses = mapItemsToDto(all);

        PageData<TripResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(buses, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<TripResponseDto> findAllBySearch(TripSearchRequest tripSearchRequest) {
        return tripService.findAllBySearch(tripSearchRequest).stream()
                .map(TripResponseDto::new)
                .collect(Collectors.toList());
    }

    private List<TripResponseDto> mapItemsToDto(DataTableResponse<Trip> all) {
        return all.getItems()
                .stream()
                .map(TripResponseDto::new)
                .collect(Collectors.toList());
    }

    private Trip getTripFromDto(TripRequestDto tripRequestDto) {
        Trip trip = new Trip();
        trip.setBus(busService.findById(tripRequestDto.getBus().getId()));
        if (tripRequestDto.getPromotion() != null) {
            trip.setPromotion(promotionService.findById(tripRequestDto.getPromotion().getId()));
        }
        trip.setRoute(routeService.findById(tripRequestDto.getRoute().getId()));
        trip.setDeparture(tripRequestDto.getDeparture());
        trip.setArrival(tripRequestDto.getArrival());
        trip.setPrice(tripRequestDto.getPrice());
        return trip;
    }


}
