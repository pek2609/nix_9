package ua.com.alevel.facade.bus;

import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.web.dto.bus.BusRequestDto;
import ua.com.alevel.web.dto.bus.BusResponseDto;

import java.util.List;

public interface BusFacade extends BaseFacade<BusRequestDto, BusResponseDto> {

    List<BusResponseDto> findAll();
}
