package ua.com.alevel.facade.impl;

import ua.com.alevel.dto.group.GroupRequestDto;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.impl.GroupServiceImpl;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class GroupFacadeImpl implements GroupFacade {

    private final GroupService groupService = new GroupServiceImpl();

    @Override
    public void create(GroupRequestDto groupRequestDto) {
        Group group = new Group();
        group.setName(groupRequestDto.getName());
        group.setTeacherName(groupRequestDto.getTeacherName());
        groupService.create(group);
    }

    @Override
    public void update(GroupRequestDto groupRequestDto, String id) {
        Group group = new Group();
        group.setId(id);
        group.setName(groupRequestDto.getName());
        group.setTeacherName(groupRequestDto.getTeacherName());
        groupService.update(group);
    }

    @Override
    public void delete(String id) {
        groupService.delete(id);
    }

    @Override
    public GroupResponseDto findById(String id) {
        return new GroupResponseDto(groupService.findById(id));
    }

    @Override
    public Collection<GroupResponseDto> findAll() throws IOException {
        return groupService.findAll().stream().map(GroupResponseDto::new).collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
