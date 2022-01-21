package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController extends AbstractController {

    private final StudentFacade studentFacade;
    private final GroupFacade groupFacade;

    public StudentController(StudentFacade studentFacade, GroupFacade groupFacade) {
        this.studentFacade = studentFacade;
        this.groupFacade = groupFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<StudentResponseDto> response = studentFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/students/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Students");
        model.addAttribute("allowCreate", true);
        model.addAttribute("createNewItemUrl", "/students/new");
//        System.out.println("response = " + response);
        return "pages/student/student_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/students", model);
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("student", new StudentRequestDto());
        return "pages/student/student_new";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        StudentResponseDto dto = studentFacade.findById(id);
        model.addAttribute("student", dto);
        model.addAttribute("deleteFromGroup", groupFacade.findByStudentsId(id));
        model.addAttribute("addToGroup", groupFacade.findNotByStudentsId(id));
        return "pages/student/student_details";
    }

    @PostMapping("/create")
    public String createNewStudent(@ModelAttribute("student") StudentRequestDto dto) {
        studentFacade.create(dto);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentFacade.delete(id);
        return "redirect:/students";
    }


    @PostMapping("/update/{id}")
    public String updateCompany(@PathVariable Long id, @ModelAttribute("student") StudentRequestDto studentRequestDto) {
        studentFacade.update(studentRequestDto, id);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        StudentResponseDto studentResponseDto = studentFacade.findById(id);
        model.addAttribute("student", studentResponseDto);
        return "pages/student/student_update";
    }

    @GetMapping("/all/group/{groupId}")
    public String findAllByCompany(@PathVariable Long groupId, Model model, WebRequest request) {
        HeaderName[] columnNames = getColumnNames();
        PageData<StudentResponseDto> response = studentFacade.findByGroupId(request, groupId);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnNames, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/students/all/group/" + groupId);
        model.addAttribute("createNewItemUrl", "/students/new");
        model.addAttribute("pageData", response);
        model.addAttribute("allowCreate", false);
        model.addAttribute("cardHeader", "All Students");
        return "pages/student/student_all";
    }

    private HeaderName[] getColumnNames() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("firstname", "firstName", "first_name"),
                new HeaderName("lastname", "lastName", "last_name"),
                new HeaderName("age", "age", "age"),
                new HeaderName("phone number", "phoneNumber", "phone_number"),
                new HeaderName("email", "email", "email"),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }


    @PostMapping("/all/group/{groupId}")
    public ModelAndView findAllByCompanyRedirect(@PathVariable Long groupId, WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/students/all/group/" + groupId, model);
    }


}
