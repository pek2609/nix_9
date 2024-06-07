package ua.com.alevel.web.controller.open;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.alevel.persistence.repository.TownRepository;

@Controller
@AllArgsConstructor
public class TownController {

    private final TownRepository townRepository;

    @GetMapping("/open/towns")
    public String showTownsOnMap(Model model) {
        model.addAttribute("towns", townRepository.findAll());
        return "pages/open/open_towns";
    }
}
