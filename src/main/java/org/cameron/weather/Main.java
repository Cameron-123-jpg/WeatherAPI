package org.cameron.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.cameron.weather.Utility.CustomBanner;
import org.cameron.weather.Utility.LastRequestService;

@SpringBootApplication
public class Main {
    private static final LastRequestService lastRequestService = new LastRequestService();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        CustomBanner customBanner = new CustomBanner();
        customBanner.printBanner(null, Main.class, System.out);

        String last = lastRequestService.loadRequest();
        if (last != null) {
            System.out.println("Last request was: " + last);
        } else {
            System.out.println("No previous request found.");
        }
    }
}
