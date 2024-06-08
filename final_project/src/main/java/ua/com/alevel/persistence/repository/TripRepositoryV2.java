package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.entity.TripV2;

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


    @Modifying
    @Transactional
    @Query(value = "update TripV2 t set t.usedSeats = t.usedSeats + :passengerCount where t.id = :tripId")
    void plusUsedSeats(Long tripId, Integer passengerCount);
}
