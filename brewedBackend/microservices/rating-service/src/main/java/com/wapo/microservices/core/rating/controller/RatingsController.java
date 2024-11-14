package com.wapo.microservices.core.rating.controller;

import com.wapo.microservices.core.rating.dto.RatingDto;
import com.wapo.microservices.core.rating.dto.ResponseDto;
import com.wapo.microservices.core.rating.service.IRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating/api")
public class RatingsController {
    @Autowired
    IRatingsService iRatingsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody RatingDto ratingDto) {
        iRatingsService.createRating(ratingDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Rating created successfully"));
    }

    @GetMapping("/list/{productId}")
    public ResponseEntity<List<RatingDto>> getRatingsList(@PathVariable String productId) {
        List<RatingDto> ratingDtoList = iRatingsService.getRatings(productId);

        return ResponseEntity.status(HttpStatus.OK).body(ratingDtoList);
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<ResponseDto> deleteRating(@PathVariable Long ratingId) {
        boolean isDeleted = iRatingsService.deleteRating(ratingId);
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
