package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public interface StudentService extends BaseService<Student> {

    DataTableResponse<Student> findByGroupId(DataTableRequest dataTableRequest, Long groupId);

    List<Student> findAll();
}
