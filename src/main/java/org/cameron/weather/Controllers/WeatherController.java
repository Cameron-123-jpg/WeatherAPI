package org.cameron.weather.Controllers;

import java.util.List;

import org.cameron.weather.DTO.WeatherDTO;
import org.cameron.weather.Services.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping ("/api/weather")
@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/zip")
    public List<WeatherDTO> getWeather(@RequestParam String zipCode) throws Exception {
        return weatherService.getNextSixHours(zipCode);
    }
}