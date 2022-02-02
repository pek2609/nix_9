package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.persistence.entity.Order;

public interface OrderRepository extends BaseRepository<Order> {

    Page<Order> findByClientId(Long clientId, Pageable pageable);

    Page<Order> findByTripId(Long tripId, Pageable pageable);

}
