package com.study.springboot.service.dto;

import java.util.List;

import com.study.springboot.entity.ReviewEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
public class ReviewDto {
    private Double avgScore;
    private List<ReviewEntity> reviews;
    private ReviewDtoPage page;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReviewDtoPage {
        private Integer offset;
        private Integer limit;
    }
}
