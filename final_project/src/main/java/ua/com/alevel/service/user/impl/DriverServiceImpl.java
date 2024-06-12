package ua.com.alevel.service.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.persistence.entity.user.Driver;
import ua.com.alevel.persistence.repository.user.DriverRepository;
import ua.com.alevel.persistence.type.Role;
import ua.com.alevel.service.user.DriverService;
import ua.com.alevel.util.SecurityUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final SecurityService securityService;
    @Override
    public List<Driver> getAvailableDrivers(LocalDateTime departure, LocalDateTime arrival) {
        return driverRepository.findAvailableDrivers(departure, arrival);
    }

    @Override
    public Driver getCurrentDriver() {
        if (!SecurityUtil.hasRole(Role.ROLE_DRIVER.name())) {
            System.out.println("CURRENT USER IS NOT DRIVER");
        }
        return driverRepository.findByEmail(securityService.getCurrentUserName());
    }
}
