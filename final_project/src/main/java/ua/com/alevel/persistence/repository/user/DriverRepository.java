package ua.com.alevel.persistence.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Driver;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DriverRepository extends UserRepository<Driver> {

    @Query("SELECT d FROM Driver d WHERE d NOT IN (" +
            "SELECT d1 FROM Driver d1 JOIN d1.trips t WHERE (" +
            "t.departure BETWEEN :departure AND :arrival OR " +
            "t.arrival BETWEEN :departure AND :arrival OR " +
            "t.departure <= :departure AND t.arrival >= :arrival" +
            "))")
    List<Driver> findAvailableDrivers(@Param("departure") LocalDateTime departure,
                                      @Param("arrival") LocalDateTime arrival);
}
