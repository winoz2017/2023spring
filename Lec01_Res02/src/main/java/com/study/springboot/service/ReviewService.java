package com.study.springboot.service;

import java.time.ZonedDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.study.springboot.entity.ReviewEntity;
import com.study.springboot.repository.RestaurantRepository;
import com.study.springboot.repository.ReviewRepository;
import com.study.springboot.service.dto.ReviewDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {
	 private final RestaurantRepository restaurantRepository;
	    private final ReviewRepository reviewRepository;

	    @Transactional
	    public void createReview(Long restaurantId, String content, Double score) {
	        restaurantRepository.findById(restaurantId).orElseThrow(); // 없는 맛집이란 에러를 냄

	        ReviewEntity review = ReviewEntity.builder()
	                .restaurantId(restaurantId)
	                .content(content)
	                .score(score)
	                .createdAt(ZonedDateTime.now())
	                .build();

	        reviewRepository.save(review);
	    }

	    @Transactional
	    public void deleteReview(Long reviewId) {
	        ReviewEntity review = reviewRepository.findById(reviewId).orElseThrow();

	        reviewRepository.delete(review);
	    }

	    public ReviewDto getRestaurantReview(Long restaurantId, Pageable page) {
	        Double avgScore = reviewRepository.getAvgScoreByRestaurantId(restaurantId);
	        Slice<ReviewEntity> reviews = reviewRepository.findSliceByRestaurantId(restaurantId, page);

	        return ReviewDto.builder()
	                .avgScore(avgScore)
	                .reviews(reviews.getContent())
	                .page(
	                        ReviewDto.ReviewDtoPage.builder()
	                                .offset(page.getPageNumber() * page.getPageSize())
	                                .limit(page.getPageSize())
	                                .build()
	                )
	                .build();
	    }
}
