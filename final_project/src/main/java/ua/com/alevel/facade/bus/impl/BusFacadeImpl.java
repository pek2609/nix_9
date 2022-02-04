package ua.com.alevel.facade.bus.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.bus.BusFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.service.bus.BusService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.web.dto.bus.BusRequestDto;
import ua.com.alevel.web.dto.bus.BusResponseDto;
import ua.com.alevel.web.dto.datatable.PageAndSizeData;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.datatable.SortData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusFacadeImpl implements BusFacade {

    private final BusService busService;

    public BusFacadeImpl(BusService busService) {
        this.busService = busService;
    }

    @Override
    public void create(BusRequestDto busRequestDto) {
        Bus bus = getBusFromDto(busRequestDto);
        busService.create(bus);
    }

    @Override
    public void update(BusRequestDto busRequestDto, Long id) {
        Bus bus = getBusFromDto(busRequestDto);
        bus.setId(id);
        busService.update(bus);
    }

    @Override
    public void delete(Long id) {
        busService.delete(id);
    }

    @Override
    public BusResponseDto findById(Long id) {
        return new BusResponseDto(busService.findById(id));
    }

    @Override
    public PageData<BusResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);

        DataTableResponse<Bus> all = busService.findAll(dataTableRequest);
        List<BusResponseDto> buses = all.getItems()
                .stream()
                .map(BusResponseDto::new)
                .collect(Collectors.toList());

        PageData<BusResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(buses, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<BusResponseDto> findAll() {
        return busService.findAll().stream().map(BusResponseDto::new).toList();
    }

    private Bus getBusFromDto(BusRequestDto busRequestDto) {
        Bus bus = new Bus();
        bus.setName(busRequestDto.getName());
        bus.setImageUrl(busRequestDto.getImageUrl());
        bus.setSeats(busRequestDto.getSeats());
        return bus;
    }
}
