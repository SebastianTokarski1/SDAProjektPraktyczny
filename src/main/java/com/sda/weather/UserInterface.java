package com.sda.weather;

import com.sda.weather.forecast.ForecastController;
import com.sda.weather.location.LocationController;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class UserInterface {

    private final Scanner scanner;
    private final LocationController locationController;
    private final ForecastController forecastController;

    public void runApplication() {
        System.out.println("Witaj w aplikacji pogodowej\n");

        while (true) {
            System.out.println("Możlliwe opcje:");
            System.out.println("1. Dodać nową lokalizację");
            System.out.println("2. Wyświetlić dodane lokalizacje");
            System.out.println("3. Wyświetlić informacje o pogodzie dla lokalizacji");
            System.out.println("4. Zamknąć aplikację");
            System.out.print("\nWybierz co chcesz zrobić: ");

            int userInput = getInteger();

            switch (userInput) {
                case 1:
                    createLocation();
                    break;
                case 2:
                    getLocation();
                    break;
                case 3:
                    getWeatherForecast();
                    break;
                case 4:
                    return;
                default:
                    System.out.print("\nWybrałeś niewłaściwą opcję. ");
            }
        }
    }

    private void getWeatherForecast() {
        System.out.print("Podaj id lokalizacji: ");
        String cityId = scanner.nextLine();
        System.out.print("Podaj dzień prognozy [1-5] (opcjonalne): ");
        String period = scanner.nextLine();

        // GET: http://mojadomena.pl/forecasts?cityId=1&perion=1
        String responseBody = forecastController.getForecast(cityId, period);
        System.out.println("Odpowiedź serwera: \n" + responseBody + "\n");
    }

    private void getLocation() {
        String responseBody = locationController.getLocations();
        responseBody = responseBody
                .replaceAll("\\{", "\n\t\\{")
                .replaceAll("}]", "}\n]");

        // GET: http://mojadomena.pl/locations
        System.out.println("Odpowiedź serwera: \n" + responseBody + "\n");
    }

    private void createLocation() {
        System.out.print("Podaj nazwe miasta: ");
        String city = scanner.nextLine();
        System.out.print("Podaj nazwe regionu (opcjonalne): ");
        String region = scanner.nextLine();
        System.out.print("Podaj nazwe kraju: ");
        String country = scanner.nextLine();
        System.out.print("Podaj szerokość geograficzną: ");
        String longitude = scanner.nextLine();
        System.out.print("Podaj długość geograficzną: ");
        String latitude = scanner.nextLine();

        // POST: http://mojadomena.pl/locations
        String requestBody = String.format("{\"city\":\"%s\",\"region\":\"%s\",\"country\":\"%s\",\"longitude\":\"%s\",\"latitude\":\"%s\"}", city, region, country, longitude, latitude);
        String response = locationController.createLocation(requestBody);
        System.out.println("Odpowiedź serwera: " + response + "\n");
    }

    private int getInteger() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (Exception e) {
                System.out.print("\nPodana wartość musi być cyfrą. Wpisz cyfrę: ");
            }
        }
    }
}
