package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.persistence.entity.Route;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.persistence.type.Town;

import java.util.List;

public interface RouteRepository extends BaseRepository<Route> {

    @Query("select r from Route r")
    Page<Route> findAll(Pageable pageable);

    boolean existsByDepartureTownAndArrivalTown(Town departureTown, Town arrivalTown);

    @Query("select t.route from Trip t where t in :trips group by t.route")
    List<Route> findAllByTripsGroupsByRoute(@Param("trips") List<Trip> trips);
}
