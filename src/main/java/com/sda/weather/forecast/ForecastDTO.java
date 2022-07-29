package com.sda.weather.forecast;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class ForecastDTO {
    private final float temperature;
    private final int pressure;
    private final int humidity;
    private final float windSpeed;
    private final String windDirection;
    private final String date;
}
