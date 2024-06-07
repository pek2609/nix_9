package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.persistence.entity.RouteV2;

public interface RouteRepositoryV2 extends BaseRepository<RouteV2> {

    @Query("SELECT r FROM RouteV2 r JOIN r.departureTown dt JOIN r.arrivalTown at WHERE lower(dt.name) like %:town% or lower(at.name) like %:town%")
    Page<RouteV2> findByTown(String town, Pageable pageable);
}
