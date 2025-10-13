package org.cameron.weather.Services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.cameron.weather.DTO.WeatherDTO;
import org.cameron.weather.Utility.WeatherHttpRequest;
import org.cameron.weather.Utility.LastRequestUtility;
import org.cameron.weather.Utility.SaveRequestUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

    private final String BASEURL;
    private final String APIKEY;
    private final WeatherHttpRequest weatherHttpRequest;
    private final ObjectMapper mapper;
    private final LastRequestUtility lastRequestService;
    private final SaveRequestUtility saveRequestUtility;

    public WeatherService(
            WeatherHttpRequest weatherHttpRequest,
            @Value("${weather.api.weatherBaseUrl}") String baseUrl,
            @Value("${weather.api.weatherKey}") String apiKey,
            LastRequestUtility lastRequestService,
            SaveRequestUtility saveRequestUtility) {
        this.weatherHttpRequest = weatherHttpRequest;
        this.BASEURL = baseUrl;
        this.APIKEY = apiKey;
        this.mapper = new ObjectMapper();
        this.lastRequestService = lastRequestService;
        this.saveRequestUtility = saveRequestUtility;
    }

    public List<WeatherDTO> getWeatherZipCode(String zipCode) throws IOException, InterruptedException {
        return getWeatherZipCode(zipCode, 6, true);
    }

    public List<WeatherDTO> getWeatherZipCode(String zipCode, int hoursRequested, boolean saveRequest) throws IOException, InterruptedException {
        if (saveRequest) {
            lastRequestService.saveRequest(zipCode);
            saveRequestUtility.saveRequest(zipCode);
        }

        String requestUrl = BASEURL + zipCode + "?key=" + APIKEY;
        String response = weatherHttpRequest.getWeatherByZipCode(zipCode, requestUrl);

        JsonNode root = mapper.readTree(response);
        JsonNode hours = root.path("days").get(0).path("hours");

        String timeZone = root.path("timezone").asText();
        int currentHour = LocalDateTime.now(ZoneId.of(timeZone)).getHour();

        List<WeatherDTO> result = new ArrayList<>();
        for (int i = 0; i < hoursRequested; i++) {
            int index = (currentHour + i) % hours.size();
            JsonNode hour = hours.get(index);

            WeatherDTO dto = new WeatherDTO(
                    hour.path("datetime").asText(),
                    hour.path("temp").asDouble(),
                    hour.path("feelslike").asDouble(),
                    hour.path("conditions").asText());
            result.add(dto);
        }
        return result;
    }

    public List<WeatherDTO> getWeatherPlace(String place) throws IOException, InterruptedException {
        return getWeatherPlace(place, 6, true);
    }

    public List<WeatherDTO> getWeatherPlace(String place, int hoursRequested) throws IOException, InterruptedException {
        return getWeatherPlace(place, hoursRequested, true);
    }

    public List<WeatherDTO> getWeatherPlace(String place, int hoursRequested, boolean saveRequest) throws IOException, InterruptedException {
        if (saveRequest) {
            lastRequestService.saveRequest(place);
            saveRequestUtility.saveRequest(place);
        }

        String requestUrl = BASEURL + place + "?key=" + APIKEY;
        String response = weatherHttpRequest.getWeatherByZipCode(place, requestUrl);

        JsonNode root = mapper.readTree(response);
        JsonNode hours = root.path("days").get(0).path("hours");

        String timeZone = root.path("timezone").asText();
        int currentHour = LocalDateTime.now(ZoneId.of(timeZone)).getHour();

        List<WeatherDTO> result = new ArrayList<>();
        for (int i = 0; i < hoursRequested; i++) {
            int index = (currentHour + i) % hours.size();
            JsonNode hour = hours.get(index);

            WeatherDTO dto = new WeatherDTO(
                    hour.path("datetime").asText(),
                    hour.path("temp").asDouble(),
                    hour.path("feelslike").asDouble(),
                    hour.path("conditions").asText());
            result.add(dto);
        }
        return result;
    }
}