package ua.com.alevel.service.order;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.service.BaseService;

public interface OrderService extends BaseService<Order> {

    DataTableResponse<Order> findByClient(Long clientId, DataTableRequest dataTableRequest);

    DataTableResponse<Order> findByTrip(Long tripId, DataTableRequest pageable);
}
