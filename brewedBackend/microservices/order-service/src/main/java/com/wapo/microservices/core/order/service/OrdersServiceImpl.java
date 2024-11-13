package com.wapo.microservices.core.order.service;

import com.wapo.microservices.core.order.dto.OrderDto;
import com.wapo.microservices.core.order.exception.OrderAlreadyExistsException;
import com.wapo.microservices.core.order.exception.ResourceNotFoundException;
import com.wapo.microservices.core.order.persistence.OrderEntity;
import com.wapo.microservices.core.order.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void createOrder(OrderDto orderDto) {
        OrderEntity order = new OrderEntity();

        order.setUserId(orderDto.getUserId());
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setProductIds(order.getProductIds());

        Optional<OrderEntity> optionalOrder = orderRepository.findByOrderNumber(order.getOrderNumber());

        if(optionalOrder.isPresent()) {
            throw new OrderAlreadyExistsException("Product already registered with given Order Number "
                    + orderDto.getOrderNumber());
        }

        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getOrders(String userId) {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        orders.forEach(o -> {
            OrderDto orderDto = new OrderDto();

            orderDto.setUserId(o.getUserId());
            orderDto.setOrderNumber(o.getOrderNumber());
            orderDto.setTotalPrice(o.getTotalPrice());
            orderDto.setProductIds(o.getProductIds());

            orderDtos.add(orderDto);
        });

        return orderDtos;
    }

    @Override
    public boolean deleteOrder(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order", "ID", orderId.toString())
        );

        orderRepository.delete(order);

        return true;
    }
}
