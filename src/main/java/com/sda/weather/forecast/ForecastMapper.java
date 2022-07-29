package com.sda.weather.forecast;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ForecastMapper {

    private final WindDirectionMapper windDirectionMapper;

    ForecastDTO asForecastDTO(Forecast forecast) {
        return ForecastDTO.builder()
                .temperature(forecast.getTemperature())
                .pressure(forecast.getPressure())
                .humidity(forecast.getHumidity())
                .windSpeed(forecast.getWindSpeed())
                .windDirection(windDirectionMapper.mapWindDirection(forecast.getWindDirection()))
                .date(forecast.getForecastDate().toString())
                .build();
    }
}
