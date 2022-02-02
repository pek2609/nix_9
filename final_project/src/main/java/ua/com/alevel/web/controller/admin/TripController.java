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
import ua.com.alevel.facade.trip.TripFacade;
import ua.com.alevel.service.bus.BusService;
import ua.com.alevel.service.promotion.PromotionService;
import ua.com.alevel.service.route.RouteService;
import ua.com.alevel.validated.annotation.ValidId;
import ua.com.alevel.web.controller.BaseController;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.trip.TripRequestDto;
import ua.com.alevel.web.dto.trip.TripResponseDto;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/trips")
public class TripController extends BaseController {

    private final TripFacade tripFacade;
    private final BusService busService;
    private final RouteService routeService;
    private final PromotionService promotionService;

    public TripController(TripFacade tripFacade, BusService busService, RouteService routeService, PromotionService promotionService) {
        this.tripFacade = tripFacade;
        this.busService = busService;
        this.routeService = routeService;
        this.promotionService = promotionService;
    }


    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = getColumnNames();
        PageData<TripResponseDto> response = tripFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/trips/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Trips");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/trips/new");
        return "pages/admin/trip/trips_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/trips", model);
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        TripResponseDto dto = tripFacade.findById(id);
        model.addAttribute("trip", dto);
        return "pages/admin/trip/trip_details";
    }

    @GetMapping("/new")
    public String redirectToNewTripPage(Model model) {
        model.addAttribute("trip", new TripRequestDto());
        model.addAttribute("buses", busService.findAll());
        model.addAttribute("routes", routeService.findAll());
        model.addAttribute("promotions", promotionService.findAll());
        return "pages/admin/trip/trip_new";
    }

    @PostMapping("/create")
    public String createNewTrip(@ModelAttribute("trip") @Valid TripRequestDto dto, BindingResult bindingResult) {
        tripFacade.create(dto);
        return "redirect:/trips";
    }

    @PostMapping("/update/{id}")
    public String updateTrip(@PathVariable @ValidId Long id, @Valid @ModelAttribute("trip") TripRequestDto dto, BindingResult bindingResult) {
        tripFacade.update(dto, id);
        return "redirect:/trips/details/" + id;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable @ValidId Long id, Model model) {
        TripResponseDto tripResponseDto = tripFacade.findById(id);
        model.addAttribute("trip", tripResponseDto);
        model.addAttribute("buses", busService.findAll());
        model.addAttribute("routes", routeService.findAll());
        model.addAttribute("promotions", promotionService.findAll());
        return "pages/admin/trip/trip_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @ValidId Long id) {
        tripFacade.delete(id);
        return "redirect:/trips";
    }

    @GetMapping("/all/bus/{busId}")
    public String findAllByBus(@PathVariable @ValidId Long busId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<TripResponseDto> response = tripFacade.findAllByBus(request, busId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/trips/all/bus/" + busId);
        model.addAttribute("createNewItemUrl", "/trips/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Trips");
        return "pages/admin/trip/trips_all";
    }


    @PostMapping("/all/bus/{busId}")
    public ModelAndView findAllByBusRedirect(@PathVariable Long busId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/trips/all/bus/" + busId, model);
    }

    @GetMapping("/all/promotion/{promotionId}")
    public String findAllByPromotion(@PathVariable @ValidId Long promotionId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<TripResponseDto> response = tripFacade.findAllByPromotion(request, promotionId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/trips/all/promotion/" + promotionId);
        model.addAttribute("createNewItemUrl", "/trips/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Trips");
        return "pages/admin/trip/trips_all";
    }


    @PostMapping("/all/promotion/{promotionId}")
    public ModelAndView findAllByPromotionRedirect(@PathVariable Long promotionId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/trips/all/promotion/" + promotionId, model);
    }

    @GetMapping("/all/route/{routeId}")
    public String findAllByRoute(@PathVariable @ValidId Long routeId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<TripResponseDto> response = tripFacade.findAllByRoute(request, routeId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/trips/all/route/" + routeId);
        model.addAttribute("createNewItemUrl", "/trips/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Trips");
        return "pages/admin/trip/trips_all";
    }


    @PostMapping("/all/route/{routeId}")
    public ModelAndView findAllByRoute(@PathVariable Long routeId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/trips/all/route/" + routeId, model);
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("bus", "bus.name", null),
                new HeaderName("route", "route.departureTown", null),
                new HeaderName("departure", "departure", "departure"),
                new HeaderName("arrival", "arrival", "arrival"),
                new HeaderName("price", "price", "price"),
                new HeaderName("order count", "orders.size", null),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }
}
