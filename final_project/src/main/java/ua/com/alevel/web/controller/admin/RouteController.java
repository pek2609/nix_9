package ua.com.alevel.web.controller.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.route.RouteFacade;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.validated.ValidId;
import ua.com.alevel.web.controller.BaseController;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.routs.RouteRequestDto;
import ua.com.alevel.web.dto.routs.RouteResponseDto;

import javax.validation.Valid;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/routes")
public class RouteController extends BaseController {

    private final RouteFacade routeFacade;

    public RouteController(RouteFacade routeFacade) {
        this.routeFacade = routeFacade;
    }


    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = getColumnNames();
        PageData<RouteResponseDto> response = routeFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/routes/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Routes");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/routes/new");
        return "pages/admin/route/routes_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/routes", model);
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        RouteResponseDto dto = routeFacade.findById(id);
        model.addAttribute("route", dto);
        return "pages/admin/route/route_details";
    }

    @GetMapping("/new")
    public String redirectToNewRoutePage(Model model) {
        model.addAttribute("route", new RouteRequestDto());
        model.addAttribute("towns", Town.values());
        return "pages/admin/route/route_new";
    }

    @PostMapping("/create")
    public String createNewRoute(@ModelAttribute("route") @Valid RouteRequestDto dto, BindingResult bindingResult) {
        routeFacade.create(dto);
        return "redirect:/routes";
    }

    @PostMapping("/update/{id}")
    public String updateRoute(@PathVariable @ValidId Long id, @ModelAttribute("route") @Valid RouteRequestDto dto, BindingResult bindingResult) {
        routeFacade.update(dto, id);
        return "redirect:/routes/details/" + id;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable @ValidId Long id, Model model) {
        RouteResponseDto routeResponseDto = routeFacade.findById(id);
        model.addAttribute("route", routeResponseDto);
        model.addAttribute("towns", Town.values());
        return "pages/admin/route/route_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @ValidId Long id) {
        routeFacade.delete(id);
        return "redirect:/routes";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("departure town", "departureTown", "departure_town"),
                new HeaderName("arrival town", "arrivalTown", "arrival_town"),
                new HeaderName("trips count", "trips.size", null),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }
}
