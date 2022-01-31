package ua.com.alevel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/dashboard")
public class ClientController {

    @GetMapping
    public String dashboard() {
        return "pages/client/dashboard";
    }
}
