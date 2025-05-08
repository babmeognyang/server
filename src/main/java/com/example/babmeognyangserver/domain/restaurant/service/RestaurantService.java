package com.example.babmeognyangserver.domain.restaurant.service;

import com.example.babmeognyangserver.domain.restaurant.api.naver.NaverApiService;
import com.example.babmeognyangserver.domain.restaurant.dto.RestaurantInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final NaverApiService naverApiService;

    public List<RestaurantInfo> searchRestaurant(String keyword){
        return naverApiService.searchPlaces(keyword);
    }
}
