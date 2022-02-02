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
import ua.com.alevel.facade.bus.BusFacade;
import ua.com.alevel.validated.annotation.ValidId;
import ua.com.alevel.web.controller.BaseController;
import ua.com.alevel.web.dto.bus.BusRequestDto;
import ua.com.alevel.web.dto.bus.BusResponseDto;
import ua.com.alevel.web.dto.datatable.PageData;

import javax.validation.Valid;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/buses")
public class BusController extends BaseController {

    private final BusFacade busFacade;

    public BusController(BusFacade busFacade) {
        this.busFacade = busFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = getColumnNames();
        PageData<BusResponseDto> response = busFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/buses/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Buses");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/buses/new");
        return "pages/admin/bus/buses_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/buses", model);
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        BusResponseDto dto = busFacade.findById(id);
        model.addAttribute("bus", dto);
        return "pages/admin/bus/bus_details";
    }

    @GetMapping("/new")
    public String redirectToNewBusPage(Model model) {
        model.addAttribute("bus", new BusRequestDto());
        return "pages/admin/bus/bus_new";
    }

    @PostMapping("/create")
    public String createNewBus(@ModelAttribute("bus") @Valid BusRequestDto dto, BindingResult bindingResult) {
        busFacade.create(dto);
        return "redirect:/buses";
    }

    @PostMapping("/update/{id}")
    public String updateBus(@PathVariable @ValidId Long id, @ModelAttribute("bus") @Valid BusRequestDto busRequestDto, BindingResult bindingResult) {
        busFacade.update(busRequestDto, id);
        return "redirect:/buses/details/"+id;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable @ValidId Long id, Model model) {
        BusResponseDto busResponseDto = busFacade.findById(id);
        model.addAttribute("bus", busResponseDto);
        return "pages/admin/bus/bus_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @ValidId Long id) {
        busFacade.delete(id);
        return "redirect:/buses";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("image", null, null),
                new HeaderName("name", "name", "name"),
                new HeaderName("number of seats", "seats", "seats"),
                new HeaderName("trips count", "trips.size", null),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }
}
