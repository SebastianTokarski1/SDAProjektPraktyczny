package com.sda.weather.forecast.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
class ForecastResponse {

    private List<DailyForecast> daily;

    @Data
    public static class DailyForecast {
        @JsonProperty("dt")
        private Long timestamp;
        @JsonProperty("temp")
        private TemperatureDefinition temperature;
        private Integer pressure;
        private Integer humidity;
        @JsonProperty("wind_speed")
        private Float windSpeed;
        @JsonProperty("wind_deg")
        private Integer windDeg;

        @Data
        public static class TemperatureDefinition {
            private Float day;
        }
    }
}
