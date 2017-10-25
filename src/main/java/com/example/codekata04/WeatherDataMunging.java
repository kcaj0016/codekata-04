package com.example.codekata04;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Class that takes a file with weather data and finds the day with the smallest temperature spread.
 */
public class WeatherDataMunging extends AbstractFileProcessing {

    private static final String FILENAME = "weather.dat";

    /**
     * {@inheritDoc}
     *
     * This processes a file with weather data and outputs the day with the smallest temperature spread.
     */
    public void processFile() {
        Map<String, Integer> dayMap = new HashMap<>();
        File file = new File(ClassLoader.getSystemResource("weather.dat").getFile());
        try {
            List<String> lines = readLines(file);
            for (String line : lines) {
                extractData(dayMap, line, 2, 3, 4, 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Integer> result = sortByValue(dayMap);
        Iterator<String> minSpread = result.keySet().iterator();
        System.out.println("Day with the smallest temperature spread: " + minSpread.next());
    }

}
