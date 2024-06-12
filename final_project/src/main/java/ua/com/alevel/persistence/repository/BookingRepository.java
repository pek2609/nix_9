package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.entity.Booking;

import java.util.List;

public interface BookingRepository extends BaseRepository<Booking> {

    Booking getByUuidAndEmail(String uuid, String email);

//    List<Booking> getBookingsByTripId(Long tripId, Specification<Booking> spec);
}
