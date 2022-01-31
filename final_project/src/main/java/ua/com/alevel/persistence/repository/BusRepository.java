package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.persistence.entity.Bus;

public interface BusRepository extends BaseRepository<Bus> {

    @Query("select b from Bus b")
    Page<Bus> findAll(Pageable pageable);
}
