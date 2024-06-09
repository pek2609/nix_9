package ua.com.alevel.persistence.repository;

import ua.com.alevel.persistence.entity.Booking;

import java.util.List;

public interface BookingRepository extends BaseRepository<Booking> {

    Booking getByUuidAndEmail(String uuid, String email);
}
