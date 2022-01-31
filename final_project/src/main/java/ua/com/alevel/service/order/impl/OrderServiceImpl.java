package ua.com.alevel.service.order.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.repository.OrderRepository;
import ua.com.alevel.service.order.OrderService;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CrudRepositoryHelper<Order, OrderRepository> crudRepositoryHelper;

    public OrderServiceImpl(OrderRepository orderRepository, CrudRepositoryHelper<Order, OrderRepository> crudRepositoryHelper) {
        this.orderRepository = orderRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    public void create(Order entity) {
        crudRepositoryHelper.create(orderRepository, entity);
    }

    @Override
    public void update(Order entity) {
        crudRepositoryHelper.update(orderRepository, entity);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(orderRepository, id);
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = crudRepositoryHelper.findById(orderRepository, id);
        if (order.isEmpty()) {
            throw new EntityNotFoundException("order is not found");
        }
        return order.get();
    }

    @Override
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(orderRepository, request);
    }
}
