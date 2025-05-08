package com.example.babmeognyangserver.domain.restaurant.controller;

import com.example.babmeognyangserver.domain.restaurant.dto.RestaurantInfo;
import com.example.babmeognyangserver.domain.restaurant.service.RestaurantService;
import com.example.babmeognyangserver.global.common.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<RestaurantInfo>>> getRestaurants(
            @RequestParam @NotBlank String keyword
    ) {
        List<RestaurantInfo> result = restaurantService.searchRestaurant(keyword);
        return ResponseEntity
                .ok(ApiResponse.success(result));
    }
}
