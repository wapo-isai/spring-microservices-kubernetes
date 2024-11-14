package com.wapo.microservices.core.product.service;

import com.wapo.microservices.core.product.dto.OrderDto;
import com.wapo.microservices.core.product.dto.ProductDetailsDto;
import com.wapo.microservices.core.product.dto.ProductDetailsRequestDto;
import com.wapo.microservices.core.product.dto.RatingDto;
import com.wapo.microservices.core.product.exception.ResourceNotFoundException;
import com.wapo.microservices.core.product.persistence.ProductEntity;
import com.wapo.microservices.core.product.persistence.ProductRepository;
import com.wapo.microservices.core.product.service.client.OrdersFeignClient;
import com.wapo.microservices.core.product.service.client.RatingsFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailsServiceImpl implements IProductDetailsService {
    private ProductRepository productRepository;
    private OrdersFeignClient ordersFeignClient;
    private RatingsFeignClient ratingsFeignClient;

    public ProductDetailsServiceImpl(ProductRepository productRepository, OrdersFeignClient ordersFeignClient, RatingsFeignClient ratingsFeignClient) {
        this.productRepository = productRepository;
        this.ordersFeignClient = ordersFeignClient;
        this.ratingsFeignClient = ratingsFeignClient;
    }

    @Override
    public ProductDetailsDto fetchProductDetails(ProductDetailsRequestDto productRequest) {
        ProductDetailsDto productDetailsDto = new ProductDetailsDto();

        ProductEntity product = productRepository.findById(productRequest.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("Product", "ID", productRequest.getProductId())
        );

        productDetailsDto.setProductCalories(product.getProductCalories());
        productDetailsDto.setProductName(product.getProductName());
        productDetailsDto.setProductDescription(product.getProductDescription());
        productDetailsDto.setProductPrice(product.getProductPrice());
        productDetailsDto.setImageUrl(product.getImageUrl());

        ResponseEntity<List<OrderDto>> ordersList = ordersFeignClient.getOrderList(productRequest.getUserId());
        productDetailsDto.setOrderDtoList(ordersList.getBody());

        ResponseEntity<List<RatingDto>> ratingsList = ratingsFeignClient.getRatingsList(productRequest.getProductId());
        productDetailsDto.setRatingDtoList(ratingsList.getBody());

        return productDetailsDto;
    }
}
