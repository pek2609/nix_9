package ua.com.alevel.facade.impl;

import ua.com.alevel.dto.groupstudent.GroupStudentRequestDto;
import ua.com.alevel.dto.groupstudent.GroupStudentResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.entity.Student;
import ua.com.alevel.facade.GroupStudentFacade;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.GroupStudentService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.service.impl.GroupServiceImpl;
import ua.com.alevel.service.impl.GroupStudentServiceImpl;
import ua.com.alevel.service.impl.StudentServiceImpl;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class GroupStudentFacadeImpl implements GroupStudentFacade {

    private final GroupStudentService groupStudentService = new GroupStudentServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final GroupService groupService = new GroupServiceImpl();

    @Override
    public void create(GroupStudentRequestDto groupStudentRequestDto) {
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setGroupId(groupStudentRequestDto.getGroupId());
        groupStudent.setStudentId(groupStudentRequestDto.getStudentId());
        groupStudentService.create(groupStudent);
    }

    @Override
    public void update(GroupStudentRequestDto groupStudentRequestDto, String id) {
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setId(id);
        groupStudent.setGroupId(groupStudentRequestDto.getGroupId());
        groupStudent.setStudentId(groupStudentRequestDto.getStudentId());
        groupStudentService.update(groupStudent);
    }

    @Override
    public void delete(String id) {
        groupStudentService.delete(id);
    }

    @Override
    public GroupStudentResponseDto findById(String id) {
        GroupStudent groupStudent = groupStudentService.findById(id);
        Group group = groupService.findById(groupStudent.getGroupId());
        Student student = studentService.findById(groupStudent.getStudentId());
        return new GroupStudentResponseDto(groupStudent.getId(), group, student);
    }

    @Override
    public Collection<GroupStudentResponseDto> findAll() throws IOException {
        return groupStudentService.findAll()
                .stream()
                .map(groupStudent -> findById(groupStudent.getId()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
