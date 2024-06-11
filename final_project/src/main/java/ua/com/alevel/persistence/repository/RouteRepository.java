package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.entity.RouteV2;
import ua.com.alevel.persistence.entity.Town;
import ua.com.alevel.persistence.entity.Trip;

import java.util.List;

public interface RouteRepository extends BaseRepository<RouteV2> {

    @Query("select r from RouteV2 r")
    Page<RouteV2> findAll(Pageable pageable);

    @Query("SELECT r FROM RouteV2 r JOIN r.departureTown dt JOIN r.arrivalTown at WHERE lower(dt.name) like %:town% or lower(at.name) like %:town%")
    Page<RouteV2> findByTown(String town, Pageable pageable);


    boolean existsByDepartureTownAndArrivalTownAndIdNot(Town departureTown, Town arrivalTown, Long id);

    @Deprecated
    @Query("select t.route from Trip t where t in :trips group by t.route")
    List<RouteV2> findAllByTripsGroupsByRoute(@Param("trips") List<Trip> trips);
}
