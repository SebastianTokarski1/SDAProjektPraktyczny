package com.sda.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.forecast.*;
import com.sda.weather.forecast.client.ForecastClient;
import com.sda.weather.forecast.client.ForecastResponseMapper;
import com.sda.weather.location.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherApplication {

    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        LocationRepository locationRepository = new LocationRepositoryImpl(sessionFactory);
        LocationService locationService = new LocationService(locationRepository);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        LocationMapper locationMapper = new LocationMapper();
        LocationController locationController = new LocationController(objectMapper, locationService, locationMapper);
        ForecastRepositoryImpl forecastRepository = new ForecastRepositoryImpl(sessionFactory);
        ForecastResponseMapper forecastResponseMapper = new ForecastResponseMapper();
        ForecastClient forecastClient = new ForecastClient(forecastResponseMapper,objectMapper);
        ForecastService forecastService = new ForecastService(locationService, forecastClient, forecastRepository);
        WindDirectionMapper windDirectionMapper = new WindDirectionMapper();
        ForecastMapper forecastMapper = new ForecastMapper(windDirectionMapper);
        ForecastController forecastController = new ForecastController(forecastService, forecastMapper, objectMapper);
        Scanner scanner = new Scanner(System.in);

        UserInterface userInterface = new UserInterface(scanner, locationController, forecastController);
        userInterface.runApplication();
    }
}
