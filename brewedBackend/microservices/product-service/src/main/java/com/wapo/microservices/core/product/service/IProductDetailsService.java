package com.wapo.microservices.core.product.service;

import com.wapo.microservices.core.product.dto.ProductDetailsDto;
import com.wapo.microservices.core.product.dto.ProductDetailsRequestDto;

public interface IProductDetailsService {
    ProductDetailsDto fetchProductDetails(ProductDetailsRequestDto productRequest);
}
