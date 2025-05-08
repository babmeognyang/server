package com.example.babmeognyangserver.domain.restaurant.api.naver;

import com.example.babmeognyangserver.domain.restaurant.api.naver.dto.NaverPlaceResponse;
import com.example.babmeognyangserver.domain.restaurant.dto.RestaurantInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NaverApiService {

    @Value("${naver.client-id}")
    private String clientId;

    @Value("${naver.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<RestaurantInfo> searchPlaces(String query) {
        String url = "https://openapi.naver.com/v1/search/local.json"
                + "?query=" + query
                + "&display=5"
                + "&sort=comment";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<NaverPlaceResponse> response =
                restTemplate.exchange(url, HttpMethod.GET, request, NaverPlaceResponse.class);

        return response.getBody().getItems().stream()
                .map(item -> RestaurantInfo.builder()
                        .name(item.getTitle())
                        .category(item.getCategory())
                        .address(item.getAddress())
                        .build())
                .toList();
    }
}

