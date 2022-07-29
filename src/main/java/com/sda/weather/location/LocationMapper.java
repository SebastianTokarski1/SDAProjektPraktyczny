package com.sda.weather.location;

public class LocationMapper {

    LocationDTO asLocationDTO(Location location) {
        return LocationDTO.builder()
                .id(location.getId())
                .city(location.getCity())
                .country(location.getCountry())
                .region(location.getRegion().orElse(null))
                .longitude(location.getLongitude())
                .latitude(location.getLatitude())
                .build();
    }
}
