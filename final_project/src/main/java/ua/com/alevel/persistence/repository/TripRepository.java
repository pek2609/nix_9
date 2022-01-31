package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.type.Town;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TripRepository extends BaseRepository<Trip> {

    @Query("select t from Trip t")
    Page<Trip> findAll(Pageable pageable);

    @Query(value = "select t from Trip t where t.bus.id=:id")
    Page<Trip> findByBusId(@Param("id") Long busId, Pageable pageable);

    @Query(value = "select t from Trip t where t.promotion.id=:id")
    Page<Trip> findByPromotionId(@Param("id") Long promotionId, Pageable pageable);

    @Query(value = "select t from Trip t where t.route.id=:id")
    Page<Trip> findByRouteId(@Param("id") Long routeId, Pageable pageable);

    @Query(value = "select t from Trip t where " +
            "t.route.departureTown=:departureTown and " +
            "t.route.arrivalTown=:arrivalTown and " +
            "t.departure between :start and :end")
    List<Trip> findBySearch(@Param("departureTown") Town departureTown, @Param("arrivalTown") Town arrivalTown, @Param("start") Date start, @Param("end") Date end);
}
