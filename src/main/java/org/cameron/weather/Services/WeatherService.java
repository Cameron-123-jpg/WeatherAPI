package org.cameron.weather.Services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.cameron.weather.DTO.WeatherDTO;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

    private final String BASEURL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private final String APIKEY = "YOUR_API_KEY";

    private final WeatherHttpRequest weatherHttpRequest;
    private final ObjectMapper mapper;

    public WeatherService(WeatherHttpRequest weatherHttpRequest) {
        this.weatherHttpRequest = weatherHttpRequest;
        this.mapper = new ObjectMapper();
    }

    public List<WeatherDTO> getNextSixHours(String zipCode) throws IOException, InterruptedException {
        String requestUrl = BASEURL + zipCode + "?key=" + APIKEY;
        String response = weatherHttpRequest.getWeatherByZipCode(zipCode, requestUrl);

        JsonNode root = mapper.readTree(response);
        JsonNode hours = root.path("days").get(0).path("hours");

        int currentHour = LocalDateTime.now(ZoneId.of("America/Chicago")).getHour();

        List<WeatherDTO> result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int index = (currentHour + i) % hours.size(); 
            JsonNode hour = hours.get(index);

            WeatherDTO dto = new WeatherDTO(
                hour.path("datetime").asText(),
                hour.path("temp").asDouble(),
                hour.path("feelslike").asDouble(),
                hour.path("conditions").asText()
            );
            result.add(dto);
        }

        return result;
    }
}