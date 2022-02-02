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
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.facade.trip.TripFacade;
import ua.com.alevel.service.client.ClientService;
import ua.com.alevel.util.PriceAndDateUtil;
import ua.com.alevel.validated.annotation.ValidId;
import ua.com.alevel.web.controller.BaseController;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.order.OrderRequestDto;
import ua.com.alevel.web.dto.order.OrderResponseDto;
import ua.com.alevel.web.dto.trip.TripResponseDto;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController {

    private final OrderFacade orderFacade;
    private final ClientService clientService;
    private final TripFacade tripFacade;

    public OrderController(OrderFacade orderFacade, ClientService clientService, TripFacade tripFacade) {
        this.orderFacade = orderFacade;
        this.clientService = clientService;
        this.tripFacade = tripFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = getColumnNames();
        PageData<OrderResponseDto> response = orderFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/orders/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Orders");
        model.addAttribute("allowCreate", false);
        model.addAttribute("createNewItemUrl", "/orders/new");
        return "pages/admin/order/orders_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/orders", model);
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        OrderResponseDto dto = orderFacade.findById(id);
        model.addAttribute("order", dto);
        return "pages/admin/order/order_details";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable @ValidId Long id, @Valid @ModelAttribute("order") OrderRequestDto dto, BindingResult bindingResult) {
        TripResponseDto trip = tripFacade.findById(dto.getTrip());
        dto.setCheck(PriceAndDateUtil.countPrice(dto.getAdults(), dto.getChildren(), trip.getPrice(), trip.getPromotion()));
        orderFacade.update(dto, id);
        return "redirect:/orders/details/" + id;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable @ValidId Long id, Model model) {
        OrderResponseDto orderResponseDto = orderFacade.findById(id);
        model.addAttribute("order", orderResponseDto);
        model.addAttribute("clients", clientService.findAll());
        return "pages/admin/order/order_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @ValidId Long id) {
        orderFacade.delete(id);
        return "redirect:/orders";
    }

    @GetMapping("/all/client/{clientId}")
    public String findAllByClient(@PathVariable @ValidId Long clientId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<OrderResponseDto> response = orderFacade.findByClient(clientId, request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/orders/all/client/" + clientId);
        model.addAttribute("createNewItemUrl", "/orders/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Orders");
        return "pages/admin/order/orders_all";
    }


    @PostMapping("/all/client/{clientId}")
    public ModelAndView findAllByClient(@PathVariable Long clientId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/trips/all/client/" + clientId, model);
    }

    @GetMapping("/all/trip/{tripId}")
    public String findAllByTrip(@PathVariable @ValidId Long tripId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<OrderResponseDto> response = orderFacade.findByTrip(tripId, request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/orders/all/trip/" + tripId);
        model.addAttribute("createNewItemUrl", "/orders/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Orders");
        return "pages/admin/order/orders_all";
    }


    @PostMapping("/all/trip/{tripId}")
    public ModelAndView findAllByTrip(@PathVariable Long tripId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/trips/all/trip/" + tripId, model);
    }


    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("trip", "trip.id", null),
                new HeaderName("first name", "firstName", "first_name"),
                new HeaderName("last name", "lastName", "last_name"),
                new HeaderName("phone number", "phoneNumber", "phone_number"),
                new HeaderName("check", "finalPrice", "final_price"),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }
}
