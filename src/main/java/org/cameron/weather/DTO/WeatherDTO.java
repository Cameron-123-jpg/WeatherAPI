package org.cameron.weather.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDTO {
    private String time;
    private double temperature;
    private double feelsLike;
    private String conditions;

    public WeatherDTO(String time, double temperature, double feelsLike, String conditions) {
        this.time = time;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.conditions = conditions;
    }
    
}
