package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/groups")
public class GroupController extends AbstractController {

    private final GroupFacade groupFacade;

    public GroupController(GroupFacade authorFacade) {
        this.groupFacade = authorFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = getColumnNames();
        PageData<GroupResponseDto> response = groupFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("headerDataList", getHeaderDataList(columnNames, response));
        model.addAttribute("createUrl", "/groups/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Groups");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/groups/new");
        return "pages/group/group_all";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("group name", "groupName", "group_name"),
                new HeaderName("teacher name", "teacherName", "teacher_name"),
                new HeaderName("course", "course", "course"),
                new HeaderName("student count", "studentCount", "student_count"),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/groups", model);
    }


    @GetMapping("/new")
    public String redirectToNewAuthorPage(Model model) {
        model.addAttribute("group", new GroupRequestDto());
        model.addAttribute("course", Course.values());
        return "pages/group/group_new";
    }

    @PostMapping("/create")
    public String createNewGroup(@ModelAttribute("group") GroupRequestDto dto) {
        groupFacade.create(dto);
        return "redirect:/groups";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        groupFacade.delete(id);
        return "redirect:/groups";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        GroupResponseDto dto = groupFacade.findById(id);
        model.addAttribute("group", dto);
        return "pages/group/group_details";
    }

    @PostMapping("/update/{id}")
    public String updateCompany(@PathVariable Long id, @ModelAttribute("group") GroupRequestDto groupRequestDto) {
        groupFacade.update(groupRequestDto, id);
        return "redirect:/groups";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        GroupResponseDto groupResponseDto = groupFacade.findById(id);
        model.addAttribute("group", groupResponseDto);
        model.addAttribute("course", Course.values());
        return "pages/group/group_update";
    }

    @GetMapping("/all/student/{studentId}")
    public String findAllByCompany(@PathVariable Long studentId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<GroupResponseDto> response = groupFacade.findByStudentId(request, studentId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/groups/all/student/" + studentId);
        model.addAttribute("createNewItemUrl", "/groups/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Groups");
        return "pages/group/group_all";
    }


    @PostMapping("/all/student/{studentId}")
    public ModelAndView findAllByCompanyRedirect(@PathVariable Long studentId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/groups/all/student/" + studentId, model);
    }
}
