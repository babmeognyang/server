package com.example.babmeognyangserver.domain.weather;

import com.example.babmeognyangserver.domain.weather.api.openweather.OpenWeatherService;
import com.example.babmeognyangserver.domain.weather.dto.WeatherInfo;
import com.example.babmeognyangserver.domain.weather.mapper.StationMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final StringRedisTemplate redisTemplate;
    private final OpenWeatherService openWeatherService;
    private final ObjectMapper objectMapper;
    private final StationMapper stationMapper;

    public WeatherInfo getWeatherByStationName(String stationName) {
        String key = "station_weather:" + stationName;

        String cached = redisTemplate.opsForValue().get(key);
        if (cached != null) {
            try {
                return objectMapper.readValue(cached, WeatherInfo.class);
            } catch (Exception e) {
                redisTemplate.delete(key);
            }
        }

        StationMapper.StationLocation location = stationMapper.getLocationByName(stationName);
        if (location == null) {
            throw new IllegalArgumentException("해당 역의 위치 정보를 찾을 수 없습니다: " + stationName);
        }

        var res = openWeatherService.getWeatherByCoordinates(
                location.getLatitude(), location.getLongitude());

        WeatherInfo weatherInfo = WeatherInfo.builder()
                .temp(res.getMain().getTemp())
                .desc(res.getWeather()[0].getDescription())
                .updated(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        try {
            redisTemplate.opsForValue().set(key,
                    objectMapper.writeValueAsString(weatherInfo), 30, TimeUnit.MINUTES);
        } catch (Exception ignored) {}

        return weatherInfo;
    }

}
