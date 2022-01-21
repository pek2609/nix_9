package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupFacadeImpl implements GroupFacade {

    private final GroupService groupService;

    public GroupFacadeImpl(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void create(GroupRequestDto groupRequestDto) {
        Group group = new Group();
        group.setGroupName(groupRequestDto.getGroupName());
        group.setTeacherName(groupRequestDto.getTeacherName());
        group.setCourse(groupRequestDto.getCourse());
        groupService.create(group);
    }

    @Override
    public void update(GroupRequestDto groupRequestDto, Long id) {
        Group group = new Group();
        group.setId(id);
        group.setGroupName(groupRequestDto.getGroupName());
        group.setTeacherName(groupRequestDto.getTeacherName());
        group.setCourse(groupRequestDto.getCourse());
        groupService.update(group);
    }

    @Override
    public void delete(Long id) {
        groupService.delete(id);
    }

    @Override
    public GroupResponseDto findById(Long id) {
        return new GroupResponseDto(groupService.findById(id));
    }

    @Override
    public PageData<GroupResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);
        DataTableResponse<Group> all = groupService.findAll(dataTableRequest);
        List<GroupResponseDto> list = mapDTRespToListOfDto(all);
        PageData<GroupResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(list, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public PageData<GroupResponseDto> findByStudentId(WebRequest request, Long studentId) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);
        DataTableResponse<Group> all = groupService.findByStudentId(dataTableRequest, studentId);
        List<GroupResponseDto> list = mapDTRespToListOfDto(all);
        PageData<GroupResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(list, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<GroupResponseDto> findAll() {
        return groupService.findAll().stream().map(GroupResponseDto::new).toList();
    }

    @Override
    public void addStudent(Long groupId, Long studentId) {
        groupService.addStudent(groupId, studentId);
    }

    @Override
    public void removeStudent(Long groupId, Long studentId) {
        groupService.removeStudent(groupId, studentId);
    }

    @Override
    public List<GroupResponseDto> findByStudentsId(Long studentId) {
        return groupService.findByStudentId(studentId).stream().map(GroupResponseDto::new).toList();
    }

    @Override
    public Set<GroupResponseDto> findNotByStudentsId(Long studentId) {
        return groupService.findNotByStudentsId(studentId).stream().map(GroupResponseDto::new).collect(Collectors.toSet());
    }

    private List<GroupResponseDto> mapDTRespToListOfDto(DataTableResponse<Group> all) {
        return all.getItems().
                stream().
                map(GroupResponseDto::new).
                peek(dto -> dto.setStudentCount((Integer) all.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());
    }
}
