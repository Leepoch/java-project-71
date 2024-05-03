package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
                if (valueFieldFile1.equals(valueFieldFile2)) {
                    var typeWithValue = new HashMap<String, Object>();
                    typeWithValue.put("type", "notChanged");
                    typeWithValue.put("value", value);
                    typeWithValue.put("key", key);
                    diff.add(typeWithValue);
                } else {
                    var typeWithValue1 = new HashMap<String, Object>();
                    var typeWithValue2 = new HashMap<String, Object>();
                    typeWithValue1.put("type", "changedFrom");
                    typeWithValue1.put("key", key);
                    typeWithValue1.put("value", parsedData1.get(key));
                    typeWithValue2.put("type", "changedTo");
                    typeWithValue2.put("value", value);
                    typeWithValue2.put("key", key);
                    diff.add(typeWithValue1);
                    diff.add(typeWithValue2);
                }
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
}
