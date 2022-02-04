package ua.com.alevel.service.bus.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.repository.BusRepository;
import ua.com.alevel.service.bus.BusService;
import ua.com.alevel.util.Messages;

import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {

    private final LoggerService loggerService;

    private final BusRepository busRepository;
    private final CrudRepositoryHelper<Bus, BusRepository> crudRepositoryHelper;

    public BusServiceImpl(LoggerService loggerService, BusRepository busRepository, CrudRepositoryHelper<Bus, BusRepository> crudRepositoryHelper) {
        this.loggerService = loggerService;
        this.busRepository = busRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Transactional
    @Override
    public void create(Bus entity) {
        crudRepositoryHelper.create(busRepository, entity);
    }

    @Transactional
    @Override
    public void update(Bus entity) {
        crudRepositoryHelper.update(busRepository, entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "bus", id, "start"));
        crudRepositoryHelper.delete(busRepository, id);
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "bus", id, "end"));
    }

    @Transactional(readOnly = true)
    @Override
    public Bus findById(Long id) {
        Optional<Bus> bus = crudRepositoryHelper.findById(busRepository, id);
        if (bus.isEmpty()) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("bus", id));
            throw new EntityNotFoundException("bus is not found");
        }
        return bus.get();
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<Bus> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(busRepository, request);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bus> findAll() {
        return crudRepositoryHelper.findAll(busRepository);
    }
}
