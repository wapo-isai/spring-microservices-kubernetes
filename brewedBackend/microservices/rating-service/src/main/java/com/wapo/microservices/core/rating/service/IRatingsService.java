package com.wapo.microservices.core.rating.service;

import com.wapo.microservices.core.rating.dto.RatingDto;

import java.util.List;

public interface IRatingsService {
    void createRating(RatingDto ratingDto);
    List<RatingDto> getRatings(String productId);
    boolean deleteRating(Long ratingId);
}
