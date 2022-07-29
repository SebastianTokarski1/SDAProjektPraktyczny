package com.sda.weather.forecast.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.forecast.Forecast;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ForecastClientTest {

    private static ForecastClient forecastClient;

    @BeforeAll
    static void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ForecastResponseMapper forecastResponseMapper = new ForecastResponseMapper();
        forecastClient = new ForecastClient(forecastResponseMapper, objectMapper);
    }

    @Test
    void getForecast_returnsForecast() {
        // given
        LocalDate forecastDate = LocalDate.now().plusDays(2);

        // when
        Optional<Forecast> result = forecastClient.getForecast(50f, 50f, forecastDate);

        // then
        assertThat(result).hasValueSatisfying(forecast -> {
            assertThat(forecast.getId()).isNull();
            assertThat(forecast.getLocation()).isNull();
            assertThat(forecast.getCreationDate()).isNull();
            assertThat(forecast.getTemperature()).isNotNull();
            assertThat(forecast.getHumidity()).isNotNull();
            assertThat(forecast.getWindSpeed()).isNotNull();
            assertThat(forecast.getWindDirection()).isNotNull();
            assertThat(forecast.getForecastDate()).isNotNull();
        });
    }
}
