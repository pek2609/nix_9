package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.RecordFacade;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.view.dto.request.RecordRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordResponseDto;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/records")
public class RecordController extends AbstractController {

    private final RecordFacade recordFacade;
    private final StudentFacade studentFacade;
    private final GroupFacade groupFacade;

    public RecordController(RecordFacade recordFacade, StudentFacade studentFacade, GroupFacade groupFacade) {
        this.recordFacade = recordFacade;
        this.studentFacade = studentFacade;
        this.groupFacade = groupFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<RecordResponseDto> response = recordFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/records/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Records");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/records/new");
        return "pages/record/record_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/records", model);
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("group name", "groupName", "group_name"),
                new HeaderName("firstname", "firstName", "first_name"),
                new HeaderName("lastname", "lastName", "last_name"),
                new HeaderName("phone number", "phoneNumber", "phone_number"),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @GetMapping("/new")
    public String redirectToNewAuthorPage(Model model) {
        model.addAttribute("record", new RecordRequestDto());
        model.addAttribute("groups", groupFacade.findAll());
        model.addAttribute("students", studentFacade.findAll());
        return "pages/record/record_new";
    }

    @PostMapping("/create")
    public String createNewRecord(@ModelAttribute("record") RecordRequestDto dto) {
        recordFacade.create(dto);
        return "redirect:/records";
    }

    @PostMapping("/update/{id}")
    public String updateRecord(@PathVariable Long id, @ModelAttribute("record") RecordRequestDto dto) {
        recordFacade.update(dto, id);
        return "redirect:/records";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        RecordResponseDto recordResponseDto = recordFacade.findById(id);
        model.addAttribute("record", recordResponseDto);
        model.addAttribute("groups", groupFacade.findAll());
        model.addAttribute("students", studentFacade.findAll());
        return "pages/record/record_update";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        RecordResponseDto dto = recordFacade.findById(id);
        model.addAttribute("record", dto);
        return "pages/record/record_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        recordFacade.delete(id);
        return "redirect:/records";
    }
}
