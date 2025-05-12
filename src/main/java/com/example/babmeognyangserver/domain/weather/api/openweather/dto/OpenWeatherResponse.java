package com.example.babmeognyangserver.domain.weather.api.openweather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OpenWeatherResponse {

    @JsonProperty("weather")
    private Weather[] weather;  // 날씨 상태 배열 (흐림, 비 등)

    @JsonProperty("main")
    private Main main;          // 온도, 체감온도, 습도 등 날씨의 주요 수치 정보

    @Getter
    @NoArgsConstructor
    public static class Weather {
        private String main;         // 예: "Clouds", "Rain" → 날씨 요약 (짧은 표현)
        private String description;  // 예: "흐림", "약한 비" → 상세한 설명
    }

    @Getter
    @NoArgsConstructor
    public static class Main {
        private double temp;         // 현재 기온 (°C)

        @JsonProperty("feels_like")
        private double feelsLike;    // 체감온도

        private int humidity;        // 습도 (%) 예: 58
    }
}
