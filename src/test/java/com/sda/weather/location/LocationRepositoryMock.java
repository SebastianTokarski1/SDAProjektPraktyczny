package com.sda.weather.location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class LocationRepositoryMock implements LocationRepository {

    private final List<Location> locations = new ArrayList<>();

    @Override
    public Location save(Location location) {
        locations.add(location);
        return location;
    }

    @Override
    public List<Location> findAll() {
        return Collections.unmodifiableList(locations);
    }

    @Override
    public Optional<Location> getLocationById(Long id) {
        return locations.stream()
                .filter(l -> l.getId().equals(id))
                .findAny();
    }

    void clear() {
        locations.clear();
    }
}
