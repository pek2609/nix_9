package ua.com.alevel.service.order.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.logger.LoggerLevel;
import ua.com.alevel.logger.LoggerService;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.repository.OrderRepository;
import ua.com.alevel.service.order.OrderService;
import ua.com.alevel.util.DataTableUtil;
import ua.com.alevel.util.Messages;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final LoggerService loggerService;
    private final OrderRepository orderRepository;
    private final CrudRepositoryHelper<Order, OrderRepository> crudRepositoryHelper;

    public OrderServiceImpl(LoggerService loggerService, OrderRepository orderRepository, CrudRepositoryHelper<Order, OrderRepository> crudRepositoryHelper) {
        this.loggerService = loggerService;
        this.orderRepository = orderRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Transactional
    @Override
    public void create(Order entity) {
        crudRepositoryHelper.create(orderRepository, entity);
    }

    @Transactional
    @Override
    public void update(Order entity) {
        crudRepositoryHelper.update(orderRepository, entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "order", id, "start"));
        crudRepositoryHelper.delete(orderRepository, id);
        loggerService.commit(LoggerLevel.WARN, Messages.entityLog("delete", "order", id, "end"));
    }

    @Transactional(readOnly = true)
    @Override
    public Order findById(Long id) {
        Optional<Order> order = crudRepositoryHelper.findById(orderRepository, id);
        if (order.isEmpty()) {
            loggerService.commit(LoggerLevel.ERROR, Messages.entityNotFoundLog("order", id));
            throw new EntityNotFoundException("order is not found");
        }
        return order.get();
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(orderRepository, request);
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<Order> findByClient(Long clientId, DataTableRequest dataTableRequest) {
        Page<Order> orderPage = orderRepository.findByClientId(clientId, DataTableUtil.formPageableByRequest(dataTableRequest));
        return DataTableUtil.formResponse(orderPage, dataTableRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public DataTableResponse<Order> findByTrip(Long tripId, DataTableRequest pageable) {
        Page<Order> orderPage = orderRepository.findByTripId(tripId, DataTableUtil.formPageableByRequest(pageable));
        return DataTableUtil.formResponse(orderPage, pageable);
    }
}
