package ua.com.alevel.web.controller.client;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.client.ClientFacade;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.facade.promotion.PromotionFacade;
import ua.com.alevel.facade.trip.TripFacade;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.type.Sex;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.service.client.ClientService;
import ua.com.alevel.util.PriceAndDateUtil;
import ua.com.alevel.validated.annotation.ValidId;
import ua.com.alevel.web.controller.BaseController;
import ua.com.alevel.web.dto.DtoResponse;
import ua.com.alevel.web.dto.client.ClientProfileRequestDto;
import ua.com.alevel.web.dto.client.ClientResponseDto;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.order.OrderRequestDto;
import ua.com.alevel.web.dto.order.OrderResponseDto;
import ua.com.alevel.web.dto.promotion.PromotionResponseDto;
import ua.com.alevel.web.dto.trip.TripResponseDto;
import ua.com.alevel.web.dto.trip.TripSearchRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/client")
public class ClientController extends BaseController {

    private final TripFacade tripFacade;
    private final OrderFacade orderFacade;
    private final PromotionFacade promotionFacade;
    private final ClientFacade clientFacade;

    private final ClientService clientService;
    private final SecurityService securityService;

    public ClientController(TripFacade tripFacade, OrderFacade orderFacade, PromotionFacade promotionFacade, ClientFacade clientFacade, ClientService clientService, SecurityService securityService) {
        this.tripFacade = tripFacade;
        this.orderFacade = orderFacade;
        this.promotionFacade = promotionFacade;
        this.clientFacade = clientFacade;
        this.clientService = clientService;
        this.securityService = securityService;
    }


    @GetMapping("/profile")
    public String dashboard(Model model) {
        Client currentClient = getCurrentClient();
        model.addAttribute("client", currentClient);
        return "pages/client/profile";
    }

    @GetMapping("/profile/update")
    public String updateProfile(Model model) {
        ClientResponseDto currentClient = new ClientResponseDto(getCurrentClient());
        model.addAttribute("client", currentClient);
        model.addAttribute("gender", Sex.values());
        return "pages/client/profile_update";
    }

    @PostMapping("/profile/update/{id}")
    public String update(@PathVariable @ValidId Long id, @ModelAttribute("client") @Valid ClientProfileRequestDto clientRequestDto, BindingResult bindingResult) {
        clientFacade.updateProfile(clientRequestDto, id);
        return "redirect:/client/profile";
    }

    @GetMapping("/tickets")
    public String tickets(Model model) {
        model.addAttribute("towns", Town.values());
        model.addAttribute("search", new TripSearchRequest());
        return "pages/client/ticket_select";
    }

    @PostMapping("/tickets/all")
    public String ticketsRedirect(Model model, @Valid @ModelAttribute("search") TripSearchRequest tripSearchRequest, BindingResult bindingResult) {
        List<TripResponseDto> all = tripFacade.findAllBySearch(tripSearchRequest);
        model.addAttribute("trips", all);
        model.addAttribute("req", tripSearchRequest);
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
        orderRequestDto.setClient(getCurrentClient());
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
    public String createNewPromotion(@ModelAttribute("order") @Valid OrderRequestDto dto, BindingResult bindingResult) {
        orderFacade.create(dto);
        return "redirect:/client/order/ok";
    }

    @GetMapping("order/ok")
    public String successfulOrder(Model model) {
        model.addAttribute("back", "/open/tickets");
        return "success";
    }

    @GetMapping("/promotions")
    public String promotions(Model model) {
        List<PromotionResponseDto> all = promotionFacade.findAll();
        all = all.stream().filter(DtoResponse::getVisible).toList();
        model.addAttribute("promotions", all);
        return "pages/client/promotions";
    }

    @GetMapping("/orders")
    public String orders(Model model, WebRequest request) {
        Client currentClient = getCurrentClient();
        HeaderName[] columnNames = getColumnNames();
        PageData<OrderResponseDto> response = orderFacade.findByClient(currentClient.getId(), request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/client/orders/all");
        model.addAttribute("createNewItemUrl", "/orders/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "My Orders");
        return "pages/client/orders_all";
    }

    @PostMapping("/orders/all")
    public ModelAndView findAllByClient(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/client/orders", model);
    }

    private Client getCurrentClient() {
        return clientService.findByEmail(securityService.getCurrentUserName());
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("created", "created", "created"),
                new HeaderName("updated", "updated", "updated"),
                new HeaderName("route", "trip.route.departureTown", null),
                new HeaderName("departure", "trip.departure", null),
                new HeaderName("arrival", "trip.arrival", null),
                new HeaderName("bus", "trip.bus.name", null),
                new HeaderName("adults", "adultsNumber", "adults_number"),
                new HeaderName("children", "childrenNumber", "children_number"),
                new HeaderName("check", "finalPrice", "final_price"),
        };
    }
}
