package com.study.springboot.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAndEditRestaurantRequest {
    private String name;
    private String address;
    private List<CreateAndEditRestaurantRequestMenu> menus;
}