package com.example.codekata04;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link WeatherDataMunging} class.
 */
public class WeatherDataMungingTest {

    private WeatherDataMunging weather;

    @Before
    public void setup() {
        weather = new WeatherDataMunging();
    }

    @Test
    public void testExtractDataSimple() {
        String line = "1 2 3";
        Map<String, Integer> result = new HashMap<>();
        weather.extractData(result, line, 1, 2, 3, 0);
        assertTrue(result.containsKey("1"));
        assertTrue(result.get("1") == 1);
    }

    @Test
    public void testExtractDataWithNoIntegers() {
        String line = "MM DD YYYY";
        Map<String, Integer> result = new HashMap<>();
        weather.extractData(result, line, 1, 2, 3, 0);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testReadFile() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("test_data.dat").getFile());
        List<String> lines = weather.readLines(file);
        assertTrue(lines.size() == 5);
    }

    @Test
    public void testMapSort() {
        Map<String, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put("4", 4);
        unsortedMap.put("2", 2);
        unsortedMap.put("1", 1);
        unsortedMap.put("3", 3);
        weather.sortByValue(unsortedMap);
        Set<String> keys = unsortedMap.keySet();
        Iterator key = keys.iterator();
        String firstKey = key.next().toString();
        assertFalse(firstKey.equalsIgnoreCase("4"));
        assertTrue(firstKey.equalsIgnoreCase("1"));
    }

}
