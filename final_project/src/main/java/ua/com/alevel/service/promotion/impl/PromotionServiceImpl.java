package ua.com.alevel.service.promotion.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.persistence.repository.PromotionRepository;
import ua.com.alevel.service.promotion.PromotionService;
import ua.com.alevel.util.Messages;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final LoggerService loggerService;
    private final PromotionRepository promotionRepository;
    private final CrudRepositoryHelper<Promotion, PromotionRepository> crudRepositoryHelper;

    public PromotionServiceImpl(LoggerService loggerService, PromotionRepository promotionRepository, CrudRepositoryHelper<Promotion, PromotionRepository> crudRepositoryHelper) {
        this.loggerService = loggerService;
        this.promotionRepository = promotionRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Transactional
    @Override
    public void create(Promotion entity) {
        crudRepositoryHelper.create(promotionRepository, entity);
    }

    @Transactional
    @Override
    public void update(Promotion entity) {
        crudRepositoryHelper.update(promotionRepository, entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "promotion", id, "start"));
        crudRepositoryHelper.delete(promotionRepository, id);
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "promotion", id, "end"));
    }

    @Transactional(readOnly = true)
    @Override
    public Promotion findById(Long id) {
        Optional<Promotion> promotion = crudRepositoryHelper.findById(promotionRepository, id);
        if (promotion.isEmpty()) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("promotion", id));
            throw new EntityNotFoundException("promotion is not found");
        }
        return promotion.get();
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<Promotion> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(promotionRepository, request);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Promotion> findAll() {
        return crudRepositoryHelper.findAll(promotionRepository);
    }
}
