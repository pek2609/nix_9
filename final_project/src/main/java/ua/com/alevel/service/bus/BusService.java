package ua.com.alevel.service.bus;

import ua.com.alevel.persistence.entity.Bus;
import ua.com.alevel.service.BaseService;

import java.util.List;

public interface BusService extends BaseService<Bus> {

    List<Bus> findAll();
}
