package ua.com.alevel.web.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.alevel.persistence.repository.TownRepository;

@Controller
@AllArgsConstructor
public class AdminTownController {

    private final TownRepository townRepository;

    @GetMapping("/admin/towns")
    public String getTowns(Model model) {
        model.addAttribute("towns", townRepository.findAll());
        return "pages/admin/town/towns";
    }
}
