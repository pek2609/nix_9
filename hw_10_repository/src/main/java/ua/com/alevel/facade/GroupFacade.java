package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Set;

public interface GroupFacade extends BaseFacade<GroupRequestDto, GroupResponseDto> {

    PageData<GroupResponseDto> findByStudentId(WebRequest request, Long studentId);

    List<GroupResponseDto> findAll();

    void addStudent(Long groupId, Long studentId);

    void removeStudent(Long groupId, Long studentId);

    List<GroupResponseDto> findByStudentsId(Long studentId);

    Set<GroupResponseDto> findNotByStudentsId(Long studentId);
}
