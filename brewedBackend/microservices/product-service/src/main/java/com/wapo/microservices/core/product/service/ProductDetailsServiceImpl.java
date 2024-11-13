package com.wapo.microservices.core.product.service;

import com.wapo.microservices.core.product.dto.ProductDetailsDto;
import com.wapo.microservices.core.product.dto.ProductDetailsRequestDto;
import com.wapo.microservices.core.product.persistence.ProductRepository;
import com.wapo.microservices.core.product.service.client.OrdersFeignClient;
import com.wapo.microservices.core.product.service.client.RatingsFeignClient;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsServiceImpl implements IProductDetailsService {
    private ProductRepository productRepository;
    private OrdersFeignClient ordersFeignClient;
    private RatingsFeignClient ratingsFeignClient;

    @Override
    public ProductDetailsDto fetchProductDetails(ProductDetailsRequestDto productRequest) {
        return null;
    }
}
