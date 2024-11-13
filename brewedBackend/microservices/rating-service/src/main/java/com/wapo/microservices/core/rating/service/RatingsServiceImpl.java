package com.wapo.microservices.core.rating.service;

import com.wapo.microservices.core.rating.dto.RatingDto;
import com.wapo.microservices.core.rating.exception.ResourceNotFoundException;
import com.wapo.microservices.core.rating.persistence.RatingEntity;
import com.wapo.microservices.core.rating.persistence.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingsServiceImpl implements IRatingsService {
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public void createRating(RatingDto ratingDto) {
        RatingEntity rating = new RatingEntity();

        rating.setRatingContent(ratingDto.getRatingContent());
        rating.setAuthor(ratingDto.getAuthor());
        rating.setProductId(ratingDto.getProductId());
        rating.setSubject(ratingDto.getSubject());

        ratingRepository.save(rating);
    }

    @Override
    public List<RatingDto> getRatings(String productId) {
        List<RatingEntity> ratings = ratingRepository.findByProductId(productId);
        List<RatingDto> ratingDtos = new ArrayList<>();

        ratings.forEach(r -> {
            RatingDto ratingDto = new RatingDto();

            ratingDto.setRatingContent(r.getRatingContent());
            ratingDto.setAuthor(r.getAuthor());
            ratingDto.setProductId(r.getProductId());
            ratingDto.setSubject(r.getSubject());

            ratingDtos.add(ratingDto);
        });

        return ratingDtos;
    }

    @Override
    public boolean deleteRating(Long ratingId) {
        RatingEntity rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new ResourceNotFoundException("Rating", "ID", ratingId.toString())
        );

        ratingRepository.delete(rating);
        return true;
    }
}
