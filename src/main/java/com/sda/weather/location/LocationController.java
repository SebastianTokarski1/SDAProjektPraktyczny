package com.sda.weather.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LocationController {

    private final ObjectMapper objectMapper;
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    public String createLocation(String requestBody) {
        try {
            LocationDTO locationDTO = objectMapper.readValue(requestBody, LocationDTO.class);
            Location location = locationService.createLocation(locationDTO.getCity(), locationDTO.getRegion(), locationDTO.getCountry(), locationDTO.getLongitude(), locationDTO.getLatitude());
            LocationDTO responseBody = locationMapper.asLocationDTO(location);
            return objectMapper.writeValueAsString(responseBody);
        } catch (Exception e) {
            return String.format("{\"status\": \"error\", \"message\": \"%s\"}", e.getMessage());
        }
    }

    public String getLocations() {
        try {
            List<LocationDTO> locations = locationService.getLocations().stream()
                    .map(locationMapper::asLocationDTO)
                    .collect(Collectors.toList());

            return objectMapper.writeValueAsString(locations);
        } catch (Exception e) {
            return String.format("{\"status\": \"error\", \"message\": \"%s\"}", e.getMessage());
        }
    }
}
