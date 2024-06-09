package ua.com.alevel.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessDeniedController {

    @GetMapping("/access-denied")
    public ModelAndView accessDenied() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", "Access Denied");
        mav.setViewName("error");
        return mav;
    }
}
