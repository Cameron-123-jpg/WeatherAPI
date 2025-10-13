package org.cameron.weather.Utility;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class LastRequestUtility {
    private static final String FILE_PATH = "last_request.txt";

    public void saveRequest(String request) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            String spacedRequest = spaceRequest(request);
            writer.write(spacedRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadRequest() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
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