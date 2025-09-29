package org.cameron.weather.Utility;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public class WeatherHttpRequest {

    private final HttpClient client;

    public WeatherHttpRequest() {
        this.client = HttpClient.newHttpClient();
    }

    public String getWeatherByZipCode(String zipCode, String requestUrl) throws IOException, InterruptedException {
        java.net.http.HttpRequest httpRequest = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
