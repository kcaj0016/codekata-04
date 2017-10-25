package com.example.codekata04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class that takes a file with weather data and finds the day with the smallest temperature spread.
 */
public class WeatherMain {

    public static void main(String[] args) {
        Map<String, Integer> dayMap = new HashMap<>();
        File file = new File(ClassLoader.getSystemResource("weather.dat").getFile());
        FileReader reader = null;
        try {
            reader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                if (tokens.length > 1) {
                    Integer maxTemp = Integer.parseInt(tokens[2].replaceAll("\\D", ""));
                    Integer minTemp = Integer.parseInt(tokens[3].replaceAll("\\D", ""));
                    Integer difference = maxTemp - minTemp;
                    dayMap.put(tokens[1], difference);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Integer> result = sortByValue(dayMap);
        Iterator<String> minSpread = result.keySet().iterator();
        System.out.println("Day with the smallest temperature spread: " + minSpread.next());
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

}
