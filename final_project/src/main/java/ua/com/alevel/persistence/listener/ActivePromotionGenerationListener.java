package ua.com.alevel.persistence.listener;

import ua.com.alevel.persistence.entity.Promotion;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.Date;

public class ActivePromotionGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generatePromotion(Promotion promotion) {
        Date cur = new Date();
        boolean active = cur.compareTo(promotion.getStart())*cur.compareTo(promotion.getEnd()) < 0;
        promotion.setActive(active);
    }
}
