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

    @Override
    public String toString() {
        return String.format(
            "%s: %.1f°F (feels like %.1f°F), %s",
            time, temperature, feelsLike, conditions
        );
    }
}