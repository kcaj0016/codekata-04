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
public class FootballDataMunging extends AbstractFileProcessing {

    /**
     * Constant for the filename to process.
     */
    private static final String FILENAME = "football.dat";

    /**
     * {@inheritDoc}
     *
     * This processes a file with football data and outputs the team with the smallest spread between points for and
     * points against.
     */
    public void processFile() {
        Map<String, Integer> pointsSpreadMap = new HashMap<>();
        File file = new File(ClassLoader.getSystemResource(FILENAME).getFile());
        try {
            List<String> lines = readLines(file);
            for (String line : lines) {
                extractData(pointsSpreadMap, line, 7, 9, 10, 2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pointsSpreadMap = sortByValue(pointsSpreadMap);
        Iterator<String> minSpread = pointsSpreadMap.keySet().iterator();
        System.out.println("Team with the smallest difference in points for and points against: " + minSpread.next());
    }

}
