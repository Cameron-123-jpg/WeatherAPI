package org.cameron.weather.Utility;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class SaveRequestUtility {
    private static final String FILE_PATH = "saved_requests.txt";

    public void saveRequest(String request) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) { 
            String spacedRequest = spaceRequest(request);
            writer.write(spacedRequest + System.lineSeparator()); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String spaceRequest(String request) {
        if (request == null || request.isEmpty())
            return request;
        if (request.length() <= 3)
            return request;
        String spaced = request.replaceAll("([a-z])([A-Z])", "$1 $2");
        String[] words = spaced.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)));
                sb.append(w.substring(1).toLowerCase());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}
