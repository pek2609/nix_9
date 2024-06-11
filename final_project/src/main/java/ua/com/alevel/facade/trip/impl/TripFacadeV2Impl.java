package ua.com.alevel.facade.trip.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.trip.TripFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.entity.user.Driver;
import ua.com.alevel.service.bus.BusService;
import ua.com.alevel.service.promotion.PromotionService;
import ua.com.alevel.service.route.RouteService;
import ua.com.alevel.service.trip.TripService;
import ua.com.alevel.service.trip.v2.TripServiceV2;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.PriceAndDateUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.web.dto.datatable.PageAndSizeData;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.datatable.SortData;
import ua.com.alevel.web.dto.trip.TripRequestDto;
import ua.com.alevel.web.dto.trip.TripResponseDto;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service(value = "v2")
@Primary
@AllArgsConstructor
public class TripFacadeV2Impl implements TripFacade {

    private final TripServiceV2 tripService;

    @Override
    public void create(TripRequestDto tripRequestDto) {
        TripV2 trip = getTripFromDto(tripRequestDto);
        tripService.create(trip);
    }

    @Override
    public void update(TripRequestDto tripRequestDto, Long id) {
        TripV2 trip = getTripFromDto(tripRequestDto);
        trip.setId(id);
        tripService.update(trip);
    }

    @Override
    public void delete(Long id) {
        tripService.delete(id);
    }

    @Override
    public TripResponseDto findById(Long id) {
        TripV2 tripV2 = tripService.findById(id);
        TripResponseDto tripResponseDto = new TripResponseDto(tripV2);
        tripResponseDto.setDrivers(tripV2.getDrivers());
        return tripResponseDto;
    }

    @Override
    public PageData<TripResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<TripV2> all = tripService.findAll(dataTableRequest);
        List<TripResponseDto> buses = mapItemsToDto(all);

        PageData<TripResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(buses, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<TripResponseDto> mapItemsToDto(DataTableResponse<TripV2> all) {
        return all.getItems()
                .stream()
                .map(TripResponseDto::new)
                .collect(Collectors.toList());
    }

    private TripV2 getTripFromDto(TripRequestDto tripRequestDto) {
        TripV2 trip = new TripV2();
        trip.setBus(tripRequestDto.getBus());
        trip.setDeparture(tripRequestDto.getDeparture());
        trip.setArrival(tripRequestDto.getArrival());
        trip.setRoute(tripRequestDto.getRoute());
        trip.setPrice(tripRequestDto.getPrice());
        trip.setUsedSeats(tripRequestDto.getUsedSeats());
        trip.setDrivers(tripRequestDto.getDrivers());
        return trip;
    }


    @Override
    public void patchDrivers(Long tripId, Set<Long> driverIds) {
        TripV2 byId = tripService.findById(tripId);
        if (byId != null && CollectionUtils.isNotEmpty(driverIds)) {
            Set<Driver> drivers = driverIds.stream().map(id -> {
                Driver driver = new Driver();
//                driver.getTrips().add(byId);
                driver.setId(id);
                return driver;
            }).collect(Collectors.toSet());
            byId.setDrivers(drivers);
            tripService.update(byId);
        }
    }

    @Override
    public PageData<TripResponseDto> findAllByBus(WebRequest request, Long busId) {
        return null;
    }

    @Override
    public PageData<TripResponseDto> findAllByPromotion(WebRequest request, Long promotionId) {
        return null;
    }

    @Override
    public PageData<TripResponseDto> findAllByRoute(WebRequest request, Long routeId) {
        return null;
    }

    @Override
    public List<TripResponseDto> findAllBySearch(TripSearchRequest tripSearchRequest) {
        return null;
    }
}
