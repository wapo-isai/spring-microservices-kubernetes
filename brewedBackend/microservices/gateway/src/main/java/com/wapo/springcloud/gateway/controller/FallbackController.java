package com.wapo.springcloud.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/fallback/product")
    public ResponseEntity<String> productServiceFallback() {
        return ResponseEntity.ok("Product Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/order")
    public ResponseEntity<String> orderServiceFallback() {
        return ResponseEntity.ok("Order Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/rating")
    public ResponseEntity<String> ratingServiceFallback() {
        return ResponseEntity.ok("Rating Service is currently unavailable. Please try again later.");
    }
}
