package ua.com.alevel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.alevel.exception.AlreadyExistEntity;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.facade.client.ClientFacade;
import ua.com.alevel.persistence.type.Role;
import ua.com.alevel.persistence.type.Sex;
import ua.com.alevel.service.security.SecurityService;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.web.dto.client.ChangePasswordRequestDto;
import ua.com.alevel.web.dto.client.ClientRegisterRequestDto;

import javax.validation.Valid;


@Validated
@Controller
public class AuthController extends BaseController {


    private final ClientFacade clientFacade;
    private final SecurityService securityService;

    public AuthController(ClientFacade clientFacade, SecurityService securityService) {
        this.clientFacade = clientFacade;
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        showMessage(model, false);
        boolean authenticated = securityService.isAuthenticated();
        if (authenticated) {
            if (SecurityUtil.hasRole(Role.ROLE_ADMIN.name())) {
                return "redirect:/clients";
            }
            if (SecurityUtil.hasRole(Role.ROLE_CLIENT.name())) {
                return "redirect:/client/tickets";
            }
        }
        if (error != null) {
            showError(model, "Your email and password is invalid.");
        }
        if (logout != null) {
            showInfo(model, "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/edit/password")
    public String changePassword(Model model) {
        showMessage(model, false);
        if (securityService.isAuthenticated()) {
            return redirectProcess(model);
        }
        model.addAttribute("authForm", new ChangePasswordRequestDto());
        return "edit_password";
    }

    @PostMapping("/edit/password")
    public String changePasswordPost(@ModelAttribute("editPassword") @Valid ChangePasswordRequestDto requestDto, BindingResult bindingResult, Model model) {
        if (!securityService.existsByEmail(requestDto.getEmail())) {
            throw new EntityNotFoundException("user with this email is not found");
        }
        clientFacade.changePassword(requestDto);
        securityService.autoLogin(requestDto.getEmail(), requestDto.getPasswordConfirm());
        return redirectProcess(model);
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectProcess(model);
        }
        model.addAttribute("authForm", new ClientRegisterRequestDto());
        model.addAttribute("gender", Sex.values());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("authForm") @Valid ClientRegisterRequestDto authForm, BindingResult bindingResult, Model model) {
        showMessage(model, false);
        if (securityService.existsByEmail(authForm.getEmail())) {
            throw new AlreadyExistEntity("user with this email is already exist");
        }
        clientFacade.create(authForm);
        securityService.autoLogin(authForm.getEmail(), authForm.getPasswordConfirm());
        return redirectProcess(model);
    }

    private String redirectProcess(Model model) {
        showMessage(model, false);
        if (SecurityUtil.hasRole(Role.ROLE_ADMIN.name())) {
            return "redirect:/clients";
        }
        if (SecurityUtil.hasRole(Role.ROLE_CLIENT.name())) {
            return "redirect:/client/tickets";
        }
        return "redirect:/login";
    }
}
