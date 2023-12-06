package com.study.springboot.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {
    private  Long restaurantId;
    private  String content;
    private  Double score;
}
