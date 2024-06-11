package ua.com.alevel.service.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.user.Driver;
import ua.com.alevel.persistence.repository.user.DriverRepository;
import ua.com.alevel.service.user.DriverService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    @Override
    public List<Driver> getAvailableDrivers(LocalDateTime departure, LocalDateTime arrival) {
        return driverRepository.findAvailableDrivers(departure, arrival);
    }
}
