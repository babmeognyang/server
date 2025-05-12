package com.example.babmeognyangserver.domain.weather.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StationMapper {

    @Getter
    public static class StationLocation {
        private final double latitude;
        private final double longitude;

        public StationLocation(double lat, double lon) {
            this.latitude = lat;
            this.longitude = lon;
        }
    }

    private final Map<String, StationLocation> nameToLocation = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        try {
            var jsonFile = new ClassPathResource("static-data/korea-subway-stations.json");
            List<Map<String, Object>> stations = objectMapper.readValue(
                    jsonFile.getInputStream(), new TypeReference<>() {}
            );

            for (Map<String, Object> station : stations) {
                String name = (String) station.get("name");
                double lat = Double.parseDouble(station.get("latitude").toString());
                double lon = Double.parseDouble(station.get("longitude").toString());

                nameToLocation.put(name, new StationLocation(lat, lon));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StationLocation getLocationByName(String stationName) {
        return nameToLocation.get(stationName);
    }
}
