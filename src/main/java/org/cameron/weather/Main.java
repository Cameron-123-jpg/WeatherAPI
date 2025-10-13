package org.cameron.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.cameron.weather.DTO.WeatherDTO;
import org.cameron.weather.Services.WeatherService;
import org.cameron.weather.Utility.CustomBanner;
import org.cameron.weather.Utility.LastRequestUtility;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        var context = SpringApplication.run(Main.class, args);
        Scanner sc = new Scanner(System.in);

        LastRequestUtility lastRequestService = context.getBean(LastRequestUtility.class);
        WeatherService weatherService = context.getBean(WeatherService.class);

        CustomBanner customBanner = new CustomBanner();
        customBanner.printBanner(null, Main.class, System.out);

        String last = lastRequestService.loadRequest();
        if (last != null) {
            System.out.println("==================================================================================================");
            System.out.println("Last request was: " + last);
            List<WeatherDTO> forecast = weatherService.getWeatherPlace(last, 6);
            System.out.println("Weather forecast for: " + last);
            forecast.forEach(System.out::println);
            System.out.println("==================================================================================================");
        } else {
            System.out.println("No previous request found.");
        }

        while (true) {
            System.out.println("==================================================================================================");
            System.out.print("Enter a request (place, hours): ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) continue;

            String place;
            int hoursRequested = 6;
            String[] parts = input.split(",");
            place = parts[0].trim();

            if (parts.length > 1) {
                try {
                    hoursRequested = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid hours input, using default 6 hours.");
                }
            }

            try {
                List<WeatherDTO> forecast = weatherService.getWeatherPlace(place, hoursRequested);
                System.out.println("==================================================================================================");
                System.out.println("Weather forecast for: " + place);
                forecast.forEach(System.out::println);
                System.out.println("==================================================================================================");
            } catch (Exception e) {
                System.out.println("Error fetching weather: " + e.getMessage());
            }
        }
    }
}