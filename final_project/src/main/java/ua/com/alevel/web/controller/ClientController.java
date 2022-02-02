package ua.com.alevel.web.controller;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.facade.promotion.PromotionFacade;
import ua.com.alevel.facade.trip.TripFacade;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.service.client.ClientService;
import ua.com.alevel.service.security.SecurityService;
import ua.com.alevel.util.Messages;
import ua.com.alevel.util.PriceAndDateUtil;
import ua.com.alevel.validated.ValidId;
import ua.com.alevel.web.dto.DtoResponse;
import ua.com.alevel.web.dto.order.OrderRequestDto;
import ua.com.alevel.web.dto.promotion.PromotionResponseDto;
import ua.com.alevel.web.dto.trip.TripResponseDto;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller
@RequestMapping("/client")
public class ClientController {

    private final TripFacade tripFacade;
    private final OrderFacade orderFacade;
    private final PromotionFacade promotionFacade;
    private final ClientService clientService;
    private final SecurityService securityService;

    public ClientController(TripFacade tripFacade, OrderFacade orderFacade, PromotionFacade promotionFacade, ClientService clientService, SecurityService securityService) {
        this.tripFacade = tripFacade;
        this.orderFacade = orderFacade;
        this.promotionFacade = promotionFacade;
        this.clientService = clientService;
        this.securityService = securityService;
    }


    @GetMapping("/profile")
    public String dashboard() {
        return "pages/client/dashboard";
    }

    @GetMapping("/tickets")
    public String tickets(Model model){
        model.addAttribute("towns", Town.values());
        model.addAttribute("search", new TripSearchRequest());
        return "pages/client/ticket_select";
    }

    @PostMapping("/tickets/all")
    public String ticketsRedirect(Model model, @Valid @ModelAttribute("search") TripSearchRequest tripSearchRequest, BindingResult bindingResult){
        List<TripResponseDto> all = tripFacade.findAllBySearch(tripSearchRequest);
        model.addAttribute("trips", all);
        model.addAttribute("req" , tripSearchRequest);
        return "pages/client/tickets";
    }

    @GetMapping("/order/new/{tripId}")
    public String redirectToNewOrderPage(
            @PathVariable @ValidId Long tripId,
            @RequestParam Integer adults,
            @RequestParam Integer children, Model model) {
        TripResponseDto trip = tripFacade.findById(tripId);
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setAdults(adults);
        orderRequestDto.setChildren(children);
        orderRequestDto.setClient(getClientIfAuthenticated());
        orderRequestDto.setPhoneNumber(orderRequestDto.getClient().getPhoneNumber());
        orderRequestDto.setName(orderRequestDto.getClient().getFirstName());
        orderRequestDto.setSurname(orderRequestDto.getClient().getLastName());
        orderRequestDto.setCheck(PriceAndDateUtil.countPrice(adults, children, trip.getPrice(), trip.getPromotion()));
        orderRequestDto.setTrip(tripId);
        model.addAttribute("order", orderRequestDto);
        model.addAttribute("trip", trip);
        return "pages/client/order_new";
    }

    @PostMapping("order/create")
    public String createNewPromotion(@ModelAttribute("order") @Valid OrderRequestDto dto , BindingResult bindingResult) {
        orderFacade.create(dto);
        return "redirect:/client/order/ok";
    }

    @GetMapping("order/ok")
    public String successfulOrder( Model model){
        model.addAttribute("back", "/open/tickets");
        return "success";
    }

    @GetMapping("/promotions")
    public String promotions(Model model) {
        List<PromotionResponseDto> all = promotionFacade.findAll();
        all = all.stream().filter(DtoResponse::getVisible).toList();
        model.addAttribute("promotions", all);
        return "pages/open/promotions";
    }

    private Client getClientIfAuthenticated() {
        return clientService.findByEmail(securityService.getCurrentUserName());
    }
}
