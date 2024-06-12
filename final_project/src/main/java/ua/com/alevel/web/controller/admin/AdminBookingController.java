package ua.com.alevel.web.controller.admin;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.booking.BookingFacade;
import ua.com.alevel.validated.annotation.ValidId;
import ua.com.alevel.web.controller.BaseController;
import ua.com.alevel.web.dto.BookingAdminResponseDto;
import ua.com.alevel.web.dto.datatable.PageData;

import java.util.Map;

@Validated
@Controller
@RequestMapping("/admin/bookings")
@AllArgsConstructor
public class AdminBookingController extends BaseController {

    private BookingFacade bookingFacade;

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = getColumnNames();
        PageData<BookingAdminResponseDto> response = bookingFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/admin/bookings/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Orders");
        model.addAttribute("allowCreate", false);
        return "pages/admin/booking/bookings_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/admin/bookings", model);
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        BookingAdminResponseDto dto = bookingFacade.findById(id);
        model.addAttribute("booking", dto);
        return "pages/admin/booking/booking_details";
    }

    private HeaderName[] getColumnNames() {
        return new BaseController.HeaderName[]{
                new BaseController.HeaderName("#", null, null),
                new BaseController.HeaderName("uuid", "uuid", null),
                new BaseController.HeaderName("status", "status", null),
                new BaseController.HeaderName("email", "email", "course"),
                new BaseController.HeaderName("phone number", "phoneNumber", "phone_number"),
                new BaseController.HeaderName("trip info", null, null),
                new BaseController.HeaderName("passengers count", "adults", null),
                new BaseController.HeaderName("total price", "totalPrice", null),
                new BaseController.HeaderName("details", null, null),
        };
    }
}
