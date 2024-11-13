package com.wapo.microservices.core.product.controller;

import com.wapo.microservices.core.product.dto.ProductDto;
import com.wapo.microservices.core.product.dto.ResponseDto;
import com.wapo.microservices.core.product.service.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductsController {
    @Autowired
    private IProductsService iProductsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createProduct(@RequestBody ProductDto productDto) {
        iProductsService.createProduct(productDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Product created successfully"));
    }

    @GetMapping(
            value = "/{productId}",
            produces = "application/json")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {
        ProductDto productDto = iProductsService.fetchProduct(productId);

        return  ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProductList() {
        List<ProductDto> productDtoList = iProductsService.fetchProducts();

        return ResponseEntity.status(HttpStatus.OK).body(productDtoList);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateProduct(@RequestBody ProductDto productDto) {
        boolean isUpdated = iProductsService.updateProduct(productDto);
        if(isUpdated) {
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "Request processed successfully"));
        }
        else {
            return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("417", "Update operation failed. Please try again or contact Dev team"));
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable String productId) {
        boolean isDeleted = iProductsService.deleteProduct(productId);
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
