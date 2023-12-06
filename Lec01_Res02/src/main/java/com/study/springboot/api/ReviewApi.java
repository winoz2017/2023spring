package com.study.springboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.study.springboot.api.request.CreateReviewRequest;
import com.study.springboot.service.ReviewService;
import com.study.springboot.service.dto.ReviewDto;

@RequiredArgsConstructor
@RestController
public class ReviewApi {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<String> createReview(@RequestBody CreateReviewRequest request) {
        try {
            reviewService.createReview(request.getRestaurantId(), request.getContent(), request.getScore());
            return ResponseEntity.status(HttpStatus.CREATED).body("Review created successfully");
        } catch (IllegalArgumentException e) {
            // 예외가 발생하면 클라이언트에게 400 Bad Request와 메시지를 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // 그 외의 예외는 500 Internal Server Error와 메시지를 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating review: " + e.getMessage());
        }
    }

    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
    }


    @GetMapping("/restaurant/{restaurantId}/reviews")
    public ReviewDto getRestaurantReviews(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return reviewService.getRestaurantReview(restaurantId, PageRequest.of(offset / limit, limit));
    }
}