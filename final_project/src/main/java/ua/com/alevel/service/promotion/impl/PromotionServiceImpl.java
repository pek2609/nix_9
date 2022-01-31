package ua.com.alevel.service.promotion.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.persistence.repository.PromotionRepository;
import ua.com.alevel.service.promotion.PromotionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final CrudRepositoryHelper<Promotion, PromotionRepository> crudRepositoryHelper;

    public PromotionServiceImpl(PromotionRepository promotionRepository, CrudRepositoryHelper<Promotion, PromotionRepository> crudRepositoryHelper) {
        this.promotionRepository = promotionRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    public void create(Promotion entity) {
        crudRepositoryHelper.create(promotionRepository, entity);
    }

    @Override
    public void update(Promotion entity) {
        crudRepositoryHelper.update(promotionRepository, entity);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(promotionRepository, id);
    }

    @Override
    public Promotion findById(Long id) {
        Optional<Promotion> promotion = crudRepositoryHelper.findById(promotionRepository, id);
        if (promotion.isEmpty()) {
            throw new EntityNotFoundException("promotion is not found");
        }
        return promotion.get();
    }

    @Override
    public DataTableResponse<Promotion> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(promotionRepository, request);
    }

    @Transactional
    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }
}
