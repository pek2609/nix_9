package ua.com.alevel.service.user;

import ua.com.alevel.persistence.entity.user.Driver;

import java.time.LocalDateTime;
import java.util.List;

public interface DriverService {

    List<Driver> getAvailableDrivers(LocalDateTime departure, LocalDateTime arrival);

    Driver getCurrentDriver();
}
