package com.wapo.microservices.core.product.service;

import com.wapo.microservices.core.product.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductsService {
    void createProduct(ProductDto productDto);

    ProductDto fetchProduct(String id);

    public List<ProductDto> fetchProducts();

    boolean updateProduct(ProductDto productDto);

    boolean deleteProduct(String id);
}
