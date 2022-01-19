package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;

import java.util.List;

public interface GroupDao extends BaseDao<Group> {

    DataTableResponse<Group> findByStudentId(DataTableRequest dataTableRequest, Long studentId);

    long countByStudentId(Long studentId);

    List<Group> findAll();
}
