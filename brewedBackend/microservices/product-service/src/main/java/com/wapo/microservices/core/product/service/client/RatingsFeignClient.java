package com.wapo.microservices.core.product.service.client;

import com.wapo.microservices.core.product.dto.RatingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("rating")
public interface RatingsFeignClient {
    @GetMapping("/api/rating/list/{productId}")
    public ResponseEntity<List<RatingDto>> getRatingsList(@PathVariable String productId);
}
