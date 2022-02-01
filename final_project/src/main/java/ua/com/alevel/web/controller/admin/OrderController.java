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
import ua.com.alevel.validated.ValidId;
import ua.com.alevel.web.controller.BaseController;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.order.OrderRequestDto;
import ua.com.alevel.web.dto.order.OrderResponseDto;

import javax.validation.Valid;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController {

    private final OrderFacade orderFacade;

    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
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
        model.addAttribute("allowCreate", true);
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
        orderFacade.update(dto, id);
        return "redirect:/order/details/" + id;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable @ValidId Long id, Model model) {
        OrderResponseDto orderResponseDto = orderFacade.findById(id);
        return "pages/admin/order/order_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @ValidId Long id) {
        orderFacade.delete(id);
        return "redirect:/trips";
    }


    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("route", "trip.route.departureTown", null),
                new HeaderName("departure", "trip.departure", null),
                new HeaderName("arrival", "trip.arrival", null),
                new HeaderName("first name", "name", "name"),
                new HeaderName("last name", "surname", "surname"),
                new HeaderName("check", "check", "check"),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }
}
