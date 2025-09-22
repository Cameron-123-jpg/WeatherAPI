package org.cameron.weather.Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public class IPHTTPRequest {
    
    private final HttpClient client;

    public IPHTTPRequest() {
        this.client = HttpClient.newHttpClient();
    }

    public void getIP(String requestUrl) throws IOException, InterruptedException {
        java.net.http.HttpRequest httpRequest = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("IP Response: " + response.body());
    }
}
