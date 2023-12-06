package com.study.springboot.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAndEditRestaurantRequestMenu {
    private String name;
    private Integer price;
    // 추가 필드가 있다면 여기에 정의
}