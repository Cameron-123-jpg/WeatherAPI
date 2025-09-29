package org.cameron.weather.Utility;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class LastRequestService {
    private static final String FILE_PATH = "last_request.txt";

    public void saveRequest(String request) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(request);
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
}