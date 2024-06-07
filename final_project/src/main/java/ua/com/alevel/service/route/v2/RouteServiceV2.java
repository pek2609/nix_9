package ua.com.alevel.service.route.v2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.persistence.entity.RouteV2;

public interface RouteServiceV2 {

    Page<RouteV2> findRoutesByTown(String town, Pageable pageable);
}
