package ua.com.alevel.web.controller.open;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.alevel.persistence.entity.RouteV2;
import ua.com.alevel.service.route.v2.RouteServiceV2;

@Controller
@AllArgsConstructor
public class RouteControllerV2 {

    private final RouteServiceV2 routeService;

    @GetMapping("/open/routes")
    public String listRoutes(Model model,
                             @PageableDefault(size = 5) Pageable pageable,
                             @RequestParam(required = false) String town) {
        Page<RouteV2> page = routeService.findRoutesByTown(town, pageable);
        model.addAttribute("page", page);
        model.addAttribute("town", town);  // Current filter
        return "pages/open/open_routes";
    }
}
