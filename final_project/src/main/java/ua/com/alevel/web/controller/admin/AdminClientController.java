package ua.com.alevel.web.controller.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.client.ClientFacade;
import ua.com.alevel.web.controller.BaseController;
import ua.com.alevel.web.dto.client.ClientResponseDto;
import ua.com.alevel.web.dto.datatable.PageData;

import java.util.Map;

@Validated
@Controller
@RequestMapping("/clients")
public class AdminClientController extends BaseController {
    
    private final ClientFacade clientFacade;

    public AdminClientController(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @GetMapping
    public String findAllClients(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = getColumnNames();
        PageData<ClientResponseDto> response = clientFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/clients/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Clients");
        model.addAttribute("allowCreate", false);
        model.addAttribute("createNewItemUrl", null);
        return "pages/admin/client/clients_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/clients", model);
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable  Long id, Model model) {
        ClientResponseDto dto = clientFacade.findById(id);
        model.addAttribute("client", dto);
        return "pages/admin/client/client_details";
    }

    @GetMapping("/ban/{id}")
    public String ban(@PathVariable Long id) {
        clientFacade.ban(id);
        return "redirect:/clients";
    }

    @GetMapping("/unban/{id}")
    public String unban(@PathVariable Long id) {
        clientFacade.unban(id);
        return "redirect:/clients";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("first name", "firstName", "first_name"),
                new HeaderName("last name", "lastName", "last_name"),
                new HeaderName("email", "email", "course"),
                new HeaderName("phone number", "phoneNumber", "phone_number"),
                new HeaderName("sex", "sex", "sex"),
                new HeaderName("details", null, null),
                new HeaderName("ban", null, null),
                new HeaderName("unban", null, null)
        };
    }
}
