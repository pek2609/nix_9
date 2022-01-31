package ua.com.alevel.service.promotion;

import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.persistence.entity.Promotion;
import ua.com.alevel.service.BaseService;

import java.util.List;

public interface PromotionService extends BaseService<Promotion> {

    List<Promotion> findAll();
}
