package com.example.codekata04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractFileProcessing {

    /**
     * Method that needs to be implemented to process the data in a file.
     */
    public abstract void processFile();

    /**
     * Reads data from a file line by line and returns a List of Strings that contains all the lines.
     *
     * @param file the {@link File} to read.
     * @return a {@link List} of Strings.
     * @throws IOException if there is an error reading the file.
     */
    List<String> readLines(final File file) throws IOException {
        List<String> result = new ArrayList<>();
        FileReader reader = null;
        reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.add(line);
        }
        return result;
    }

    /**
     * Sorts a Map by the values in ascending order.
     *
     * @param map a {@link Map} with a String as the key and Integer as the value.
     * @return a {@link Map} with the same data sorted in ascending order by value.
     */
    Map<String, Integer> sortByValue(Map<String, Integer> map) {
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

    /**
     * Processes the line and adds the appropriate data to the result Map.
     *
     * @param result a {@Map} with Strings as the keys and Integers as the values
     * @param line a String, the line to process
     * @param firstToken the index of the first value to use
     * @param secondToken the index of the second value to use
     * @param minlength the minimum length of the number of tokens generated from the line.
     * @param keyToken the index of the token to use as the key.
     */
    void extractData(final Map<String, Integer> result, final String line, final int firstToken,
                             final int secondToken, final int minlength, final int keyToken) {
        String[] tokens = line.split("\\s+");
        if (tokens.length >= minlength) {
            String first = tokens[firstToken].replaceAll("\\D", "");
            String second = tokens[secondToken].replaceAll("\\D", "");
            if (!"".equalsIgnoreCase(first) && !"".equalsIgnoreCase(second)) {
                Integer firstValue = Integer.parseInt(first);
                Integer secondValue = Integer.parseInt(second);
                Integer difference;
                if (secondValue > firstValue) {
                    difference = secondValue - firstValue;
                } else {
                    difference = firstValue - secondValue;
                }
                result.put(tokens[keyToken], difference);
            }
        }
    }
}
