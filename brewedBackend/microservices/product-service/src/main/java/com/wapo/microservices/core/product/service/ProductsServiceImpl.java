package com.wapo.microservices.core.product.service;

import com.wapo.microservices.core.product.dto.ProductDto;
import com.wapo.microservices.core.product.exception.ProductAlreadyExistsException;
import com.wapo.microservices.core.product.exception.ResourceNotFoundException;
import com.wapo.microservices.core.product.persistence.ProductEntity;
import com.wapo.microservices.core.product.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements IProductsService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto productDto) {
        ProductEntity product = new ProductEntity();

        product.setProductCalories(productDto.getProductCalories());
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductPrice(productDto.getProductPrice());
        product.setImageUrl(productDto.getImageUrl());

        Optional<ProductEntity> optionalProduct = productRepository.findById(productDto.getId());

        if(optionalProduct.isPresent()) {
            throw new ProductAlreadyExistsException("Product already registered with given id "
                    + productDto.getId());
        }

        ProductEntity savedProduct = productRepository.save(product);
    }

    @Override
    public ProductDto fetchProduct(String id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Product", "ID", id)
        );

        ProductDto productDto = new ProductDto();
        productDto.setProductCalories(product.getProductCalories());
        productDto.setProductName(product.getProductName());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setImageUrl(product.getImageUrl());

        return productDto;
    }

    @Override
    public List<ProductDto> fetchProducts() {
        List<ProductEntity> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        products.forEach(e -> {
            ProductDto productDto = new ProductDto();
            productDto.setProductCalories(e.getProductCalories());
            productDto.setProductName(e.getProductName());
            productDto.setProductDescription(e.getProductDescription());
            productDto.setProductPrice(e.getProductPrice());
            productDto.setImageUrl(e.getImageUrl());

            productDtos.add(productDto);
        });

        return productDtos;
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        boolean isUpdated = false;

        if(productDto.getId() != null) {
            ProductEntity product = productRepository.findById(productDto.getId()).orElseThrow(
                    () -> new ResourceNotFoundException("Product", "ID", productDto.getId())
            );

            product.setProductCalories(productDto.getProductCalories());
            product.setProductName(productDto.getProductName());
            product.setProductDescription(productDto.getProductDescription());
            product.setProductPrice(productDto.getProductPrice());
            product.setImageUrl(productDto.getImageUrl());

            product = productRepository.save(product);

            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteProduct(String id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "ID", id)
        );

        productRepository.delete(product);
        return true;
    }
}
