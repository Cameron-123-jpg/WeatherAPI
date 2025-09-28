package org.cameron.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.cameron.weather.Utility.CustomBanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        CustomBanner customBanner = new CustomBanner();
        customBanner.printBanner(null, Main.class, System.out);
    }
}