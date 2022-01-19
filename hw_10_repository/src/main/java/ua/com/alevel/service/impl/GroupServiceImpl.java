package ua.com.alevel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.repository.GroupRepository;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.util.DataTableUtil;

import java.util.List;
import java.util.Optional;


@Service
public class GroupServiceImpl implements GroupService {

    private final CrudRepositoryHelper<Group, GroupRepository> crudRepositoryHelper;
    private final GroupRepository repository;

    public GroupServiceImpl(CrudRepositoryHelper<Group, GroupRepository> crudRepositoryHelper, GroupRepository repository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.repository = repository;
    }


    @Override
    public void create(Group entity) {
        crudRepositoryHelper.create(repository, entity);
    }

    @Override
    public void update(Group entity) {
        crudRepositoryHelper.update(repository, entity);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(repository, id);
    }

    @Override
    public Group findById(Long id) {
        Optional<Group> group = crudRepositoryHelper.findById(repository, id);
        if (group.isEmpty()) {
            throw new EntityNotFoundException("group is not found");
        }
        return group.get();
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {
        DataTableResponse<Group> all = crudRepositoryHelper.findAll(repository, request);
        DataTableUtil.setOtherParamMapStudCount(all);
        return all;
    }

    @Override
    public DataTableResponse<Group> findByStudentId(DataTableRequest dataTableRequest, Long studentId) {
        Page<Group> groupPage = repository.findByStudentsId(studentId, DataTableUtil.formPageableByRequest(dataTableRequest));
        DataTableResponse<Group> groupDataTableResponse = DataTableUtil.formResponse(groupPage, dataTableRequest);
        DataTableUtil.setOtherParamMapStudCount(groupDataTableResponse);
        return groupDataTableResponse;
    }

    @Override
    public List<Group> findAll() {
        return crudRepositoryHelper.findAll(repository);
    }
}
