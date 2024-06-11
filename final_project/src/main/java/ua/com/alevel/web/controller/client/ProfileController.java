package ua.com.alevel.web.controller.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.client.ClientFacade;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.type.Sex;
import ua.com.alevel.service.user.ClientService;
import ua.com.alevel.validated.annotation.ValidId;
import ua.com.alevel.web.dto.client.ClientProfileRequestDto;
import ua.com.alevel.web.dto.client.ClientResponseDto;

import javax.validation.Valid;

@Validated
@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private final ClientService clientService;
    private final ClientFacade clientFacade;

    @GetMapping
    public String dashboard(Model model) {
        Client currentClient = clientService.getCurrentClient();
        model.addAttribute("client", currentClient);
        return "pages/client/profile";
    }

    @GetMapping("/update")
    public String toUpdateProfile(Model model) {
        ClientResponseDto currentClient = new ClientResponseDto(clientService.getCurrentClient());
        model.addAttribute("client", currentClient);
        model.addAttribute("gender", Sex.values());
        return "pages/client/profile_update";
    }

    @PostMapping("/update/{id}")
    public String updateProfile(@PathVariable @ValidId Long id, @ModelAttribute("client") @Valid ClientProfileRequestDto clientRequestDto, BindingResult bindingResult) {
        clientFacade.updateProfile(clientRequestDto, id);
        return "redirect:/profile";
    }

}
