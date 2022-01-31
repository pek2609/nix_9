package ua.com.alevel.web.dto.order;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Trip;
import ua.com.alevel.web.dto.DtoResponse;

import java.util.Date;

public class OrderResponseDto extends DtoResponse {


    public OrderResponseDto(Long id, Date created, Date updated, Boolean visible) {
        super(id, created, updated, visible);
    }
}
