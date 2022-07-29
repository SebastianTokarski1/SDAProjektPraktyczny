package com.sda.weather.location;

import com.sda.weather.exception.BadRequestException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Optional<Location> getLocationById(String cityId) {
        try {
            long id = Long.parseLong(cityId);
            return locationRepository.getLocationById(id);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    List<Location> getLocations() {
        return locationRepository.findAll();
    }

    Location createLocation(String city, String region, String country, Float longitude, Float latitude) {
        if (city == null || city.isBlank()) {
            throw new BadRequestException("City value cannot be null");
        }
        if (country == null || country.isBlank()) {
            throw new BadRequestException("Country value cannot be null");
        }
        if (longitude == null) {
            throw new BadRequestException("Longitude value cannot be null");
        }
        if (latitude == null) {
            throw new BadRequestException("Latitude value cannot be null");
        }
        if (longitude > 90 || longitude < -90) {
            throw new BadRequestException("Szerokość geograficzna musi mieścić się w przedziale <-90; 90>");
        }
        if (latitude > 180 || latitude < -180) {
            throw new BadRequestException("Szerokość geograficzna musi mieścić się w przedziale <-180; 180>");
        }
        if (region != null && region.isBlank()) {
            region = null;
        }

        Location location = new Location(city, region, country, longitude, latitude);
        return locationRepository.save(location);
    }
}
