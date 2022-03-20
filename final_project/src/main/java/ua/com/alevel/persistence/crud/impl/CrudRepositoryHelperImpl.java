package ua.com.alevel.persistence.crud.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.util.DataTableUtil;
import ua.com.alevel.util.Messages;

import java.util.List;
import java.util.Optional;

@Service
public class CrudRepositoryHelperImpl<
        E extends BaseEntity,
        R extends BaseRepository<E>>
        implements CrudRepositoryHelper<E, R> {

    private final LoggerService loggerService;

    public CrudRepositoryHelperImpl(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Override
    public void create(R repository, E entity) {
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("create", entity.getClass().getSimpleName(), "start"));
        repository.save(entity);
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("create", entity.getClass().getSimpleName(), "end"));
    }

    @Override
    public void update(R repository, E entity) {
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("update", entity.getClass().getSimpleName(), "start"));
        checkExist(repository, entity.getId());
        repository.save(entity);
        loggerService.commit(LoggerLevel.INFO, Messages.entityLog("update", entity.getClass().getSimpleName(), "end"));
    }

    @Override
    public void delete(R repository, Long id) {
        checkExist(repository, id);
        repository.deleteById(id);
    }

    @Override
    public Optional<E> findById(R repository, Long id) {
        return repository.findById(id);
    }

    @Override
    public DataTableResponse<E> findAll(R repository, DataTableRequest dataTableRequest) {
        PageRequest pageRequest = DataTableUtil.formPageableByRequest(dataTableRequest);
        Page<E> pageEntity = repository.findAll(pageRequest);
        return DataTableUtil.formResponse(pageEntity, dataTableRequest);
    }

    @Override
    public List<E> findAll(R repository) {
        return repository.findAll();
    }

    private void checkExist(R repository, Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("entity not found");
        }
    }
}
