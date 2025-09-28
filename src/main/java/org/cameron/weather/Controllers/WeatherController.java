package org.cameron.weather.Controllers;

import java.util.List;

import org.cameron.weather.DTO.WeatherDTO;
import org.cameron.weather.Services.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/api/weather")
@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/zip")
    public List<WeatherDTO> getWeather(@RequestParam String zipCode, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();
        System.out.println("LOG: Request from IP " + ipAddress + " for zip code: " + zipCode);
        return weatherService.getWeatherZipCode(zipCode);
    }

    @GetMapping("/place")
    public List<WeatherDTO> getPlace(@RequestParam String place, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();
        System.out.println("LOG: Request from IP " + ipAddress + " for location: " + place);
        return weatherService.getWeatherPlace(place);
    }
}