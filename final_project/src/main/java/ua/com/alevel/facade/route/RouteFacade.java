package ua.com.alevel.facade.route;

import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.web.dto.route.RouteRequestDto;
import ua.com.alevel.web.dto.route.RouteResponseDto;

import java.util.List;

public interface RouteFacade extends BaseFacade<RouteRequestDto, RouteResponseDto> {

    List<RouteResponseDto> findAll();

}
