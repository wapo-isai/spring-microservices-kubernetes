package com.wapo.microservices.core.rating.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<RatingEntity, Long> {
    List<RatingEntity> findByProductId(String productId);
}
