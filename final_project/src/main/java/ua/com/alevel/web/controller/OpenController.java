package ua.com.alevel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.promotion.PromotionFacade;
import ua.com.alevel.facade.trip.TripFacade;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.web.dto.DtoResponse;
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

    public OpenController(PromotionFacade promotionFacade, TripFacade tripFacade) {
        this.promotionFacade = promotionFacade;
        this.tripFacade = tripFacade;
    }


    @GetMapping
    private String allBooks(Model model, WebRequest webRequest) {
//        model.addAttribute("bookList", plpFacade.search(webRequest));
        return "pages/open/open";
    }

    @GetMapping("/tickets")
    public String tickets(Model model){
        model.addAttribute("towns", Town.values());
        model.addAttribute("search", new TripSearchRequest());
        return "pages/open/ticket_select";
    }

    @PostMapping("/tickets/all")
    public String ticketsRedirect(Model model, @Valid @ModelAttribute("search") TripSearchRequest tripSearchRequest, BindingResult bindingResult){
        List<TripResponseDto> all = tripFacade.findAllBySearch(tripSearchRequest);
        model.addAttribute("trips", all);
        return "pages/open/tickets";
    }

    @GetMapping("/promotions")
    public String promotions(Model model){
        List<PromotionResponseDto> all = promotionFacade.findAll();
        all = all.stream().filter(DtoResponse::getVisible).toList();

        model.addAttribute("promotions", all);
        return "pages/open/promotions";
    }
}
