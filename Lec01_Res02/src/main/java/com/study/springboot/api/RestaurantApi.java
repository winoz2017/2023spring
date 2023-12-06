package com.study.springboot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.CreateAndEditRestaurantRequest;
import com.study.springboot.api.response.RestaurantDetailView;
import com.study.springboot.api.response.RestaurantView;
import com.study.springboot.entity.RestaurantEntity;
import com.study.springboot.repository.RestaurantRepository;
import com.study.springboot.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {
	@Autowired
	private final RestaurantService restaurantService;
	
	@GetMapping("/restaurants")
    public List<RestaurantView> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurant/{id}")
    public RestaurantDetailView getRestaurant(
            @PathVariable Long id
    ) {
        return restaurantService.getRestaurantDetail(id);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<String> createRestaurant(@RequestBody CreateAndEditRestaurantRequest request) {
        try {
            restaurantService.createRestaurant(request);
            return ResponseEntity.ok("Restaurant created successfully");
        } catch (Exception e) {
            // 예외가 발생하면 클라이언트에게 오류 응답을 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error creating restaurant: " + e.getMessage());
        }
    }

    @PutMapping("/restaurant/{id}")
    public void editRestaurant(
            @PathVariable Long id,
            @RequestBody CreateAndEditRestaurantRequest request
    ) {
      restaurantService.editRestaurant(id, request);
    }

    @DeleteMapping("/restaurant/{id}")
    public void deleteRestaurant(
            @PathVariable Long id
    ) {
      restaurantService.deleteRestaurant(id);
    }
}
