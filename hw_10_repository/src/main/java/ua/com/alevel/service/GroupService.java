package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;

import java.util.List;
import java.util.Set;

public interface GroupService extends BaseService<Group> {

    DataTableResponse<Group> findByStudentId(DataTableRequest dataTableRequest, Long studentId);

    List<Group> findAll();

    void addStudent(Long groupId, Long studentId);

    void removeStudent(Long groupId, Long studentId);

    List<Group> findByStudentId(Long studentId);

    Set<Group> findNotByStudentsId(Long studentId);

}
