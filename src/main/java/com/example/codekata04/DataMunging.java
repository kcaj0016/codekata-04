package com.example.codekata04;

public class DataMunging {

    /**
     * Implementation of {@link AbstractFileProcessing} class that processes a file with Football data
     */
    private static final FootballDataMunging FOOTBALL = new FootballDataMunging();

    /**
     * Implementation of {@link AbstractFileProcessing} class that processes a file with weather data
     */
    private static final WeatherDataMunging WEATHER = new WeatherDataMunging();

    /**
     * Main method that takes an argument specifying which data to process.
     *
     * @param args a String[] that contains the list of command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("football")) {
            FOOTBALL.processFile();
        } else {
            WEATHER.processFile();
        }
    }

}
