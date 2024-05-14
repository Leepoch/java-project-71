package hexlet.code;

import java.util.*;

public class Tree {
    public static ArrayList<HashMap<String, Object>> generateTree(
            String data1, String data2, String fileFormat
    ) throws Exception {
        var parsedData1 = Parser.parse(data1, fileFormat);
        var parsedData2 = Parser.parse(data2, fileFormat);
        Map<String, Object> mergedData = new HashMap<>();
        mergedData.putAll(parsedData1);
        mergedData.putAll(parsedData2);
        var diff = new ArrayList<HashMap<String, Object>>();
        var sortedMergedData = new TreeMap<>(mergedData);
        sortedMergedData.forEach((key, value) -> {
            if (parsedData1.containsKey(key) && parsedData2.containsKey(key)) {
                var valueFieldFile1 = String.valueOf(parsedData1.get(key));
                var valueFieldFile2 = String.valueOf(parsedData2.get(key));
                var typeWithValue = new HashMap<String, Object>();
                if (isEqual(valueFieldFile1, valueFieldFile2)) {
                    typeWithValue.put("type", "notChanged");
                    typeWithValue.put("value", value);
                    typeWithValue.put("key", key);
                } else {
                    typeWithValue.put("type", "changed");
                    typeWithValue.put("key", key);
                    typeWithValue.put("value1", parsedData1.get(key));
                    typeWithValue.put("value2", value);
                }
                diff.add(typeWithValue);
            } else if (parsedData1.containsKey(key) && !parsedData2.containsKey(key)) {
                var typeWithValue = new HashMap<String, Object>();
                typeWithValue.put("type", "deleted");
                typeWithValue.put("value", value);
                typeWithValue.put("key", key);
                diff.add(typeWithValue);
            } else if (!parsedData1.containsKey(key) && parsedData2.containsKey(key)) {
                var typeWithValue = new HashMap<String, Object>();
                typeWithValue.put("type", "added");
                typeWithValue.put("value", value);
                typeWithValue.put("key", key);
                diff.add(typeWithValue);
            }
        });
        return diff;
    }

    private static Boolean isEqual(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return value1 == value2;
        }
        return value1.equals(value2);
    }
}
