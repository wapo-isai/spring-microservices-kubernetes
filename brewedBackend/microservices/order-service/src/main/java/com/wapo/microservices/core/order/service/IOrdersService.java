package com.wapo.microservices.core.order.service;

import com.wapo.microservices.core.order.dto.OrderDto;

import java.util.List;

public interface IOrdersService {
    void createOrder(OrderDto orderDto);
    List<OrderDto> getOrders(String userId);
    boolean deleteOrder(Long orderId);
}
