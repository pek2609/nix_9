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
import ua.com.alevel.facade.promotion.PromotionFacade;
import ua.com.alevel.validated.ValidId;
import ua.com.alevel.web.controller.BaseController;
import ua.com.alevel.web.dto.datatable.PageData;
import ua.com.alevel.web.dto.promotion.PromotionRequestDto;
import ua.com.alevel.web.dto.promotion.PromotionResponseDto;

import javax.validation.Valid;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/promotions")
public class PromotionController extends BaseController {

    private final PromotionFacade promotionFacade;

    public PromotionController(PromotionFacade promotionFacade) {
        this.promotionFacade = promotionFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = getColumnNames();
        PageData<PromotionResponseDto> response = promotionFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/promotions/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Promotions");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/promotions/new");
        return "pages/admin/promotion/promotions_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/promotions", model);
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @ValidId Long id, Model model) {
        PromotionResponseDto dto = promotionFacade.findById(id);
        model.addAttribute("promotion", dto);
        return "pages/admin/promotion/promotion_details";
    }

    @GetMapping("/new")
    public String redirectToNewPromotionPage(Model model) {
        model.addAttribute("promotion", new PromotionRequestDto());
        return "pages/admin/promotion/promotion_new";
    }

    @PostMapping("/create")
    public String createNewPromotion(@ModelAttribute("promotion") @Valid PromotionRequestDto dto , BindingResult bindingResult) {
        promotionFacade.create(dto);
        return "redirect:/promotions";
    }

    @PostMapping("/update/{id}")
    public String updatePromotion(@PathVariable @ValidId Long id, @ModelAttribute("promotion") @Valid PromotionRequestDto dto, BindingResult bindingResult) {
        promotionFacade.update(dto, id);
        return "redirect:/promotions/details/" + id;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        PromotionResponseDto promotionResponseDto = promotionFacade.findById(id);
        model.addAttribute("promotion", promotionResponseDto);
        return "pages/admin/promotion/promotion_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @ValidId Long id) {
        promotionFacade.delete(id);
        return "redirect:/promotions";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("image", null, null),
                new HeaderName("name", "name", "name"),
                new HeaderName("active", null, null),
                new HeaderName("start", "start", "start"),
                new HeaderName("end", "end", "end"),
                new HeaderName("percent", "percent", "percent"),
                new HeaderName("trip count", "trips.size", null),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }
}
