package com.wapo.microservices.core.product.service.client;

import com.wapo.microservices.core.product.dto.OrderDto;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("order")
public interface OrdersFeignClient {
    @Retry(name="order")
    @TimeLimiter(name = "order")
    @GetMapping("/api/order/list/{userId}")
    public ResponseEntity<List<OrderDto>> getOrderList(@PathVariable String userId);
}
