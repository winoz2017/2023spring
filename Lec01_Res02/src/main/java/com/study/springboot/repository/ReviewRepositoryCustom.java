package com.study.springboot.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.study.springboot.entity.ReviewEntity;

public interface ReviewRepositoryCustom {

    Double getAvgScoreByRestaurantId(Long restaurantId);
    Slice<ReviewEntity> findSliceByRestaurantId(Long restaurantId, Pageable page);
}
