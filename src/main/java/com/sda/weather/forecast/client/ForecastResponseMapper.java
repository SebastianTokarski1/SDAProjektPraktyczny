package com.sda.weather.forecast.client;

import com.sda.weather.forecast.Forecast;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ForecastResponseMapper {

    public Forecast asForecast(ForecastResponse.DailyForecast dailyForecast) {
        LocalDate forecastDate = asLocalDate(dailyForecast.getTimestamp());

        Forecast forecast = new Forecast();
        forecast.setHumidity(dailyForecast.getHumidity());
        forecast.setPressure(dailyForecast.getPressure());
        forecast.setTemperature(dailyForecast.getTemperature().getDay());
        forecast.setWindDirection(dailyForecast.getWindDeg());
        forecast.setWindSpeed(dailyForecast.getWindSpeed());
        forecast.setForecastDate(forecastDate);
        return forecast;
    }

    LocalDate asLocalDate(Long timestampValue) {
        Timestamp timestamp = new Timestamp(timestampValue * 1000);
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
