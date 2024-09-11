package org.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java ReportGenerator <path_values> <path_tests> <path_report>");
            System.exit(1);
        }

        String pathValues = args[0];
        String pathTests = args[1];
        String pathReport = args[2];

        try {
            JSONObject valuesObj = parseJsonToDict(pathValues);
            JSONObject testsObj = parseJsonToDict(pathTests);

            JSONArray values = (JSONArray) valuesObj.get("values");
            JSONArray tests = (JSONArray) testsObj.get("tests");

            Map<Long, String> formattedValues = formatValues(values);
            createReport(tests, formattedValues);

            saveToFile(pathReport, testsObj);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject parseJsonToDict(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            return (JSONObject) parser.parse(reader);
        }
    }

    private static void saveToFile(String filePath, JSONObject data) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data.toJSONString());
        }
    }

    private static Map<Long, String> formatValues(JSONArray values) {
        Map<Long, String> formattedValues = new HashMap<>();
        for (Object obj : values) {
            JSONObject value = (JSONObject) obj;
            Long id = (Long) value.get("id");
            if (id != null) {
                formattedValues.put(id, (String) value.get("value"));
            }
        }
        return formattedValues;
    }

    private static void createReport(JSONArray tests, Map<Long, String> values) {
        for (Object obj : tests) {
            JSONObject test = (JSONObject) obj;
            JSONArray objValues = (JSONArray) test.get("values");
            if (objValues != null) {
                createReport(objValues, values);
            }
            Long objId = (Long) test.get("id");
            if (objId != null && values.containsKey(objId)) {
                test.put("value", values.get(objId));
            }
        }
    }
}