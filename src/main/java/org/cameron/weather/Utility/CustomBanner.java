package org.cameron.weather.Utility;

import com.github.lalyos.jfiglet.FigletFont;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class CustomBanner implements Banner {

    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        out.println(BLUE + "=================================================" + RESET);
        out.println(GREEN +
                "  \\ \\      / /__| | ___ ___  _ __ ___   ___   \n" +
                "   \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\  \n" +
                "    \\ V  V /  __/ | (_| (_) | | | | | |  __/  \n" +
                "     \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|  " + RESET);
        out.println(BLUE + "=================================================" + RESET);

        out.println(GREEN + "         WEATHER API  -  System Running" + RESET);
    }
}