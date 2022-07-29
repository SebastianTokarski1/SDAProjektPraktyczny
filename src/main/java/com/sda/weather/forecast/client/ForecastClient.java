package com.sda.weather.forecast.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.forecast.Forecast;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ForecastClient {

    private static final String URL = "https://api.openweathermap.org/data/2.5/onecall?lat=%s&lon=%s&appid=a7f56c4235d1e36534313e315ea5e35a&exclude=current,minutely,hourly&units=metric";
    private final ForecastResponseMapper forecastResponseMapper;
    private final ObjectMapper objectMapper;

    public Optional<Forecast> getForecast(Float latitude, Float longitude, LocalDate forecastDate) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format(URL, latitude, longitude)))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();

            ForecastResponse forecastResponse = objectMapper.readValue(responseBody, ForecastResponse.class);

            return forecastResponse.getDaily().stream()
                    .filter(f -> forecastResponseMapper.asLocalDate(f.getTimestamp()).isEqual(forecastDate))
                    .map(forecastResponseMapper::asForecast)
                    .findFirst();
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
