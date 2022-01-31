package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.persistence.entity.Promotion;

import java.util.List;

public interface PromotionRepository extends BaseRepository<Promotion> {

    @Query("select p from Promotion p")
    Page<Promotion> findAll(Pageable pageable);
}
