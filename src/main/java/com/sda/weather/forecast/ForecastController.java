package com.sda.weather.forecast;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;
    private final ForecastMapper forecastMapper;
    private final ObjectMapper objectMapper;

    public String getForecast(String locationId, String period) {
        try {
            Forecast forecast = forecastService.getForecast(locationId, period);
            ForecastDTO forecastDTO = forecastMapper.asForecastDTO(forecast);
            return objectMapper.writeValueAsString(forecastDTO);
        } catch (Exception e) {
            return String.format("{\"status\": \"error\", \"message\": \"%s\"}", e.getMessage());
        }
    }
}
