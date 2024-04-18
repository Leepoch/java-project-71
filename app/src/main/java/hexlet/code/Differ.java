package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static ArrayList<HashMap<String, Object>> getDiff(Map<String, Object> fileData1, Map<String, Object> fileData2) {
        Map<String, Object> mergedData = new HashMap<>(fileData1);
        mergedData.putAll(fileData2);
        var diff = new ArrayList<HashMap<String, Object>>();
        Map<String, Object> sortedMergedData = new TreeMap<>(mergedData);
        sortedMergedData.forEach((key, value) -> {
            if (fileData1.containsKey(key) && fileData2.containsKey(key)) {
                var valueFieldFile1 = String.valueOf(fileData1.get(key));
                var valueFieldFile2 = String.valueOf(fileData2.get(key));
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
                    typeWithValue1.put("value", fileData1.get(key));
                    typeWithValue2.put("type", "changedTo");
                    typeWithValue2.put("value", value);
                    typeWithValue2.put("key", key);
                    diff.add(typeWithValue1);
                    diff.add(typeWithValue2);
                }
            } else if (fileData1.containsKey(key) && !fileData2.containsKey(key)) {
                var typeWithValue = new HashMap<String, Object>();
                typeWithValue.put("type", "deleted");
                typeWithValue.put("value", value);
                typeWithValue.put("key", key);
                diff.add(typeWithValue);
            } else if (!fileData1.containsKey(key) && fileData2.containsKey(key)) {
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
