package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public interface StudentDao extends BaseDao<Student> {

    DataTableResponse<Student> findByGroupId(DataTableRequest dataTableRequest, Long groupId);

    long countByGroupId(Long groupId);

    List<Student> findAll();
}
