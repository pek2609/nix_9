package ua.com.alevel.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.com.alevel.exception.NonActiveTripException;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.facade.promotion.PromotionFacade;
import ua.com.alevel.facade.trip.TripFacade;
import ua.com.alevel.persistence.type.Role;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.service.security.SecurityService;
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
public class OpenController {

    private final PromotionFacade promotionFacade;
    private final TripFacade tripFacade;
    private final OrderFacade orderFacade;
    private final SecurityService securityService;

    public OpenController(PromotionFacade promotionFacade, TripFacade tripFacade, OrderFacade orderFacade, SecurityService securityService) {
        this.promotionFacade = promotionFacade;
        this.tripFacade = tripFacade;
        this.orderFacade = orderFacade;
        this.securityService = securityService;
    }

    @GetMapping("/tickets")
    public String tickets(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectClientRejectAdmin("/client/tickets");
        }
        model.addAttribute("towns", Town.values());
        model.addAttribute("search", new TripSearchRequest());
        return "pages/open/ticket_select";
    }

    @PostMapping("/tickets/all")
    public String ticketsRedirect(Model model, @Valid @ModelAttribute("search") TripSearchRequest tripSearchRequest, BindingResult bindingResult) {
        List<TripResponseDto> all = tripFacade.findAllBySearch(tripSearchRequest);
        model.addAttribute("trips", all);
        model.addAttribute("req", tripSearchRequest);
        return "pages/open/tickets";
    }

    @GetMapping("/promotions")
    public String promotions(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectClientRejectAdmin("/client/promotions");
        }
        List<PromotionResponseDto> all = promotionFacade.findAll();
        all = all.stream().filter(DtoResponse::getVisible).toList();
        model.addAttribute("promotions", all);
        return "pages/open/promotions";
    }

    @GetMapping("/order/new/{tripId}")
    public String redirectToNewOrderPage(@PathVariable @ValidId Long tripId,
                                         @RequestParam Integer adults,
                                         @RequestParam Integer children, Model model) {
        if (securityService.isAuthenticated()) {
            return redirectClientRejectAdmin("/client/order/new/" + tripId + "?adults=" + adults + "&children=" + children);
        }
        TripResponseDto trip = tripFacade.findById(tripId);
        if (!trip.getVisible()) {
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

    @PostMapping("order/create")
    public String createNewOrder(@ModelAttribute("order") @Valid OrderRequestDto dto, BindingResult bindingResult) {
        orderFacade.create(dto);
        return "redirect:/open/order/ok";
    }

    @GetMapping("order/ok")
    public String successfulOrder(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectClientRejectAdmin("/client/order/ok");
        }
        model.addAttribute("back", "/client/tickets");
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
