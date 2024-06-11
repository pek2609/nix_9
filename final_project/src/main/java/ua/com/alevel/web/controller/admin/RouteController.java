package ua.com.alevel.web.controller.admin;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.route.RouteFacade;
import ua.com.alevel.file.FileService;
import ua.com.alevel.persistence.repository.TownRepository;
import ua.com.alevel.validated.annotation.ValidId;
import ua.com.alevel.web.controller.BaseController;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.route.RouteRequestDto;
import ua.com.alevel.web.dto.route.RouteResponseDto;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/admin/routes")
@AllArgsConstructor
public class RouteController extends BaseController {

    private final RouteFacade routeFacade;
    private final TownRepository townRepository;
    private final FileService fileService;

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = getColumnNames();
        PageData<RouteResponseDto> response = routeFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/admin/routes/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Routes");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/admin/routes/new");
        return "pages/admin/route/routes_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/admin/routes", model);
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
        model.addAttribute("towns", townRepository.findAll());
        return "pages/admin/route/route_new";
    }

    @PostMapping("/create")
    public String createNewRoute(@ModelAttribute("route") @Valid RouteRequestDto dto,
                                 @RequestParam("image") MultipartFile imageFile,
                                 BindingResult bindingResult) {

        if (!imageFile.isEmpty()) {
            String routeImageDir = "/image/route";
            String s = fileService.saveMultipartFile(routeImageDir, imageFile);
            dto.setImagePath(s);  // Set the image path in the DTO
        }

        routeFacade.create(dto);
        return "redirect:/admin/routes";
    }

    @PostMapping("/update/{id}")
    public String updateRoute(@PathVariable @ValidId Long id, @ModelAttribute("route") @Valid RouteRequestDto dto,
                              @RequestParam(value = "image", required = false) MultipartFile imageFile,
                              BindingResult bindingResult) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String routeImageDir = "/image/route";
            String s = fileService.saveMultipartFile(routeImageDir, imageFile);
            dto.setImagePath(s);  // Set the image path in the DTO
        }

        routeFacade.update(dto, id);
        return "redirect:/admin/routes/details/" + id;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable @ValidId Long id, Model model) {
        RouteResponseDto routeResponseDto = routeFacade.findById(id);
        RouteRequestDto routeRequestDto = new RouteRequestDto(routeResponseDto.getDepartureTown().getId(), routeResponseDto.getArrivalTown().getId(), routeResponseDto.getDescription(), routeResponseDto.getImagePath());
        model.addAttribute("routeId", routeResponseDto.getId());
        model.addAttribute("route", routeRequestDto);
        model.addAttribute("towns", townRepository.findAll());
        return "pages/admin/route/route_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @ValidId Long id) {
        routeFacade.delete(id);
        return "redirect:/admin/routes";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("image", null, null),
                new HeaderName("departure town", "departureTown.name", "departure_town"),
                new HeaderName("arrival town", "departureTown.name", "arrival_town"),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }
}
