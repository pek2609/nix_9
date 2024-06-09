package ua.com.alevel.web.controller.open;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.exception.NonActiveTripException;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.facade.promotion.PromotionFacade;
import ua.com.alevel.facade.trip.TripFacade;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.repository.TownRepository;
import ua.com.alevel.persistence.type.Role;
import ua.com.alevel.service.trip.v2.SearchTripResult;
import ua.com.alevel.service.trip.v2.TripServiceV2;
import ua.com.alevel.util.PriceAndDateUtil;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.validated.annotation.ValidId;
import ua.com.alevel.web.dto.DtoResponse;
import ua.com.alevel.web.dto.order.OrderRequestDto;
import ua.com.alevel.web.dto.promotion.PromotionResponseDto;
import ua.com.alevel.web.dto.trip.TripResponseDto;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller
@RequestMapping("/open")
@AllArgsConstructor
public class OpenController {

    private final LoggerService loggerService;
    private final PromotionFacade promotionFacade;
    private final TripFacade tripFacade;
    private final OrderFacade orderFacade;
    private final SecurityService securityService;
    private final TownRepository townRepository;
    private final TripServiceV2 tripServiceV2;

    @GetMapping("/tickets")
    public String tickets(Model model) {
        model.addAttribute("towns", townRepository.findAll());
        model.addAttribute("search", new TripSearchRequest());
        return "pages/open/open_search";
    }

    @PostMapping("/tickets")
    public String ticketsRedirect(Model model, @Valid @ModelAttribute("search") TripSearchRequest tripSearchRequest, BindingResult bindingResult) {
//        List<TripResponseDto> all = tripFacade.findAllBySearch(tripSearchRequest);
        List<SearchTripResult> results = tripServiceV2.searchTrips(tripSearchRequest);
        model.addAttribute("results", results);
        model.addAttribute("towns", townRepository.findAll());
        model.addAttribute("search", tripSearchRequest);
        return "pages/open/open_search";
    }

    @Deprecated
    @GetMapping("/promotions")
    public String promotions(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectClientRejectAdmin("/client/promotions");
        }
        List<PromotionResponseDto> all = promotionFacade.findAll();
        all = all.stream().filter(DtoResponse::getVisible).toList();
        model.addAttribute("promotions", all);
        return "pages/open/open_search";
    }

    @Deprecated
    @GetMapping("/order/new/{tripId}")
    public String redirectToNewOrderPage(@PathVariable @ValidId Long tripId,
                                         @RequestParam Integer adults,
                                         @RequestParam Integer children, Model model) {
        if (securityService.isAuthenticated()) {
            return redirectClientRejectAdmin("/client/order/new/" + tripId + "?adults=" + adults + "&children=" + children);
        }
        TripResponseDto trip = tripFacade.findById(tripId);
        if (!trip.getVisible()) {
            loggerService.commit(LoggerLevel.ERROR, "trip is not active, id = " + trip.getId());
            throw new NonActiveTripException("trip is not active");
        }
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setAdults(adults);
        orderRequestDto.setChildren(children);
        orderRequestDto.setTrip(trip.getId());
        orderRequestDto.setCheck(PriceAndDateUtil.countPrice(adults, children, trip.getPrice(), trip.getPromotion()));
        model.addAttribute("order", orderRequestDto);
        model.addAttribute("trip", trip);
        return "pages/open/order_new";
    }

    @Deprecated
    @PostMapping("order/create")
    public String createNewOrder(@ModelAttribute("order") @Valid OrderRequestDto dto, BindingResult bindingResult) {
        orderFacade.create(dto);
        return "redirect:/open/order/ok";
    }

    @Deprecated
    @GetMapping("order/ok")
    public String successfulOrder(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectClientRejectAdmin("/client/order/ok");
        }
        model.addAttribute("back", "/open/tickets");
        return "success";
    }

    private String redirectClientRejectAdmin(String clientUrl) {
        String redirect = "";
        if (SecurityUtil.hasRole(Role.ROLE_ADMIN.name())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find page");
        }
        if (SecurityUtil.hasRole(Role.ROLE_CLIENT.name())) {
            redirect = "redirect:" + clientUrl;
        }
        return redirect;
    }
}
