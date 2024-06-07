package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.entity.TripV2;
import ua.com.alevel.persistence.type.Town;
import ua.com.alevel.service.trip.v2.SearchTripResult;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface TripRepositoryV2 extends BaseRepository<TripV2> {

    @Query("select t from Trip t")
    Page<TripV2> findAll(Pageable pageable);
    @Query(value = "select t from TripV2 t where t.bus.id=:id")
    Page<TripV2> findByBusId(@Param("id") Long busId, Pageable pageable);

    @Query(value = "select t from TripV2 t where t.route.id=:id")
    Page<Trip> findByRouteId(@Param("id") Long routeId, Pageable pageable);

    @Query(value = "select t from TripV2 t join t.bus b join t.route r join r.departureTown dt join r.arrivalTown at where " +
            "dt.id=:departureTownId and " +
            "at.id=:arrivalTownId and " +
            "DATE(t.departure) =:departure and (b.seats - t.usedSeats) >= :needSeats")
    List<TripV2> findBySearch(Long departureTownId, Long arrivalTownId, Date departure, int needSeats);
}
