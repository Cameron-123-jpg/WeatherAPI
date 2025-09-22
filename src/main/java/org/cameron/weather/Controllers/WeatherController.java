package org.cameron.weather.Controllers;

import java.util.List;

import org.cameron.weather.DTO.WeatherDTO;
import org.cameron.weather.Services.IPHTTPRequest;
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
    private final IPHTTPRequest ip;
    
    private final String ipUrl;

    public WeatherController(WeatherService weatherService, IPHTTPRequest ip, @Value("${weather.api.ipBaseUrl}") String ipUrl) {
        this.weatherService = weatherService;
        this.ip = ip;
        this.ipUrl = ipUrl;
    }

    @GetMapping("/zip")
    public List<WeatherDTO> getWeather(@RequestParam String zipCode, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();
        System.out.println("LOG: Request from IP " + ipAddress + " for zip code: " + zipCode);
        ip.getIP(ipUrl + ipAddress);
        return weatherService.getWeatherZipCode(zipCode);
    }

    @GetMapping("/place")
    public List<WeatherDTO> getPlace(@RequestParam String place, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();
        System.out.println("LOG: Request from IP " + ipAddress + " for location: " + place);
        ip.getIP(ipUrl + ipAddress);
        return weatherService.getWeatherPlace(place);
    }
}