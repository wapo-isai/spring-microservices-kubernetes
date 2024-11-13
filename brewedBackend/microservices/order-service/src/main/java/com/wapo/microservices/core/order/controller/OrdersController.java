package com.wapo.microservices.core.order.controller;

import com.wapo.microservices.core.order.dto.OrderDto;
import com.wapo.microservices.core.order.dto.ResponseDto;
import com.wapo.microservices.core.order.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrdersController {
    @Autowired
    IOrdersService iOrdersService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody OrderDto orderDto) {
        iOrdersService.createOrder(orderDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Order created successfully"));
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<OrderDto>> getOrderList(@PathVariable String userId) {
        List<OrderDto> orderDtoList = iOrdersService.getOrders(userId);

        return ResponseEntity.status(HttpStatus.OK).body(orderDtoList);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ResponseDto> deleteOrder(@PathVariable Long orderId) {
        boolean isDeleted = iOrdersService.deleteOrder(orderId);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("200", "Request processed successfully"));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("417", "Delete operation failed. Please try again or contact Dev team"));
        }
    }
}
