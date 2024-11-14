package com.wapo.microservices.core.product.controller;


import com.wapo.microservices.core.product.dto.ProductDetailsDto;
import com.wapo.microservices.core.product.dto.ProductDetailsRequestDto;
import com.wapo.microservices.core.product.dto.ProductDto;
import com.wapo.microservices.core.product.service.IProductDetailsService;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/api")
public class ProductDetailsController {

    IProductDetailsService iProductDetailsService;

    public ProductDetailsController(IProductDetailsService iProductDetailsService) {
        this.iProductDetailsService = iProductDetailsService;
    }

    @GetMapping("/fetchProductDetails")
    public ResponseEntity<ProductDetailsDto> fetchProductDetails(@RequestBody ProductDetailsRequestDto productRequest) {
        ProductDetailsDto productDetailsDto = iProductDetailsService.fetchProductDetails(productRequest);
        return ResponseEntity.status(HttpStatus.SC_OK).body(productDetailsDto);
    }
}
