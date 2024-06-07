package ua.com.alevel.service.route.v2;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.RouteV2;
import ua.com.alevel.persistence.repository.RouteRepositoryV2;

@AllArgsConstructor
@Service
public class RouteServiceV2Impl implements RouteServiceV2 {

    private final RouteRepositoryV2 routeRepository;

    public Page<RouteV2> findRoutesByTown(String town, Pageable pageable) {
        Page<RouteV2> page;
        if (StringUtils.isNotBlank(town)) {
            page = routeRepository.findByTown(town, pageable);
        } else {
            page = routeRepository.findAll(pageable);
        }

        return page;
    }
}
