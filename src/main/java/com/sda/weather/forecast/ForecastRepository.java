package com.sda.weather.forecast;

import com.sda.weather.location.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ForecastRepository {
    Forecast save(Forecast forecast);

    Optional<Forecast> findActiveForecast(Location location, LocalDateTime createdDate, LocalDate forecastDate);
}
