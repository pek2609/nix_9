package ua.com.alevel.web.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.client.ClientFacade;
import ua.com.alevel.web.dto.client.ClientResponseDto;
import ua.com.alevel.web.dto.datatable.PageData;

import java.util.Map;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminController extends BaseController {

    @GetMapping
    public String dashboard() {
        return "pages/admin/dashboard";
    }

}
