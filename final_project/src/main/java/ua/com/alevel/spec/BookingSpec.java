package ua.com.alevel.spec;

import org.springframework.data.jpa.domain.Specification;
import ua.com.alevel.persistence.entity.Booking;
import ua.com.alevel.persistence.entity.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingSpec {

    public static Specification<Booking> userIdEquals(Long clientId) {
        return (root, query, criteriaBuilder) -> clientId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("user").get("id"), clientId);
    }
    public static Specification<Booking> statusEquals(BookingStatus bookingStatus) {
        return (root, query, criteriaBuilder) -> bookingStatus == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("status"), bookingStatus);
    }

    public static Specification<Booking> hasUUIDLike(String uuid) {
        return (root, query, criteriaBuilder) ->
                uuid == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.like(root.get("uuid"), "%" + uuid + "%");
    }

    public static Specification<Booking> departureAfter(LocalDate from) {
        return (root, query, criteriaBuilder) ->
                from == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.greaterThanOrEqualTo(root.get("trip").get("departure"), LocalDateTime.of(from, LocalTime.MIN));
    }

    public static Specification<Booking> departureBefore(LocalDate to) {
        return (root, query, criteriaBuilder) ->
                to == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.lessThanOrEqualTo(root.get("trip").get("departure"), LocalDateTime.of(to, LocalTime.MAX));
    }

    public static Specification<Booking> excludeHistory(boolean fetchHistory) {
        return (root, query, criteriaBuilder) ->
                !fetchHistory ? criteriaBuilder.greaterThan(root.get("trip").get("departure"), LocalDateTime.now()) :
                        criteriaBuilder.conjunction();
    }
}
