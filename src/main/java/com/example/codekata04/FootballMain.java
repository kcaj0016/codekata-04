package com.example.codekata04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class that takes a file with weather data and finds the day with the smallest temperature spread.
 */
public class FootballMain {

    public static void main(String[] args) {
        Map<String, Integer> pointsSpreadMap = new HashMap<>();
        File file = new File(ClassLoader.getSystemResource("football.dat").getFile());
        FileReader reader = null;
        try {
            reader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                if (tokens.length > 10) {
                    Integer pointsFor = Integer.parseInt(tokens[7].replaceAll("\\D", ""));
                    Integer pointsAgainst = Integer.parseInt(tokens[9].replaceAll("\\D", ""));
                    Integer difference;
                    if (pointsAgainst >= pointsFor) {
                        difference = pointsAgainst - pointsFor;
                    } else {
                        difference = pointsFor - pointsAgainst;
                    }
                    if (pointsSpreadMap.isEmpty()) {
                        pointsSpreadMap.put(tokens[2], difference);
                    } else {
                        for (Integer v : pointsSpreadMap.values()) {
                            if (difference <= v) {
                                pointsSpreadMap.clear();
                                pointsSpreadMap.put(tokens[2], difference);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<String> minSpread = pointsSpreadMap.keySet().iterator();
        System.out.println("Team with the smallest difference in points for and points against: " + minSpread.next());
        while (minSpread.hasNext()) {
            System.out.println("                                                            And : " + minSpread.next());
        }
    }

}
