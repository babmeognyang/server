package com.example.babmeognyangserver.domain.restaurant.api.naver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NaverPlaceResponse {

    private List<Item> items;

    @Getter
    @NoArgsConstructor
    public static class Item {
        private String title;     // 상호명
        private String category;  // 업종 분류
        private String address;   // 주소
        private String roadAddress;
    }
}
