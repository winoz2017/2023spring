package com.study.springboot.api.response;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantView {
	private final Long id;
    private final String name;
    private final String address;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
}
