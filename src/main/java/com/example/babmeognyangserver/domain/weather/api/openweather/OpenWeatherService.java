package com.example.babmeognyangserver.domain.weather.api.openweather;

import com.example.babmeognyangserver.domain.weather.api.openweather.dto.OpenWeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OpenWeatherService {

    @Value("${openweather.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public OpenWeatherResponse getWeatherByCoordinates(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/2.5/weather"
                + "?lat=" + lat
                + "&lon=" + lon
                + "&appid=" + apiKey
                + "&units=metric"
                + "&lang=kr";

        ResponseEntity<OpenWeatherResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                OpenWeatherResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new IllegalStateException("OpenWeather API 호출 실패");
        }

        return response.getBody();
    }

}
