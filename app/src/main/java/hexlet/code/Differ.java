package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static ArrayList<HashMap<String, Object>> generate(Map<String, Object> file1, Map<String, Object> file2) {
        Map<String, Object> mergedData = new HashMap<>(file1);
        mergedData.putAll(file2);
        var diff = new ArrayList<HashMap<String, Object>>();
        Map<String, Object> sortedMergedData = new TreeMap<>(mergedData);
        sortedMergedData.forEach((key, value) -> {
            if (file1.containsKey(key) && file2.containsKey(key)) {
                var valueFieldFile1 = String.valueOf(file1.get(key));
                var valueFieldFile2 = String.valueOf(file2.get(key));
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
                    typeWithValue1.put("value", file1.get(key));
                    typeWithValue2.put("type", "changedTo");
                    typeWithValue2.put("value", value);
                    typeWithValue2.put("key", key);
                    diff.add(typeWithValue1);
                    diff.add(typeWithValue2);
                }
            } else if (file1.containsKey(key) && !file2.containsKey(key)) {
                var typeWithValue = new HashMap<String, Object>();
                typeWithValue.put("type", "deleted");
                typeWithValue.put("value", value);
                typeWithValue.put("key", key);
                diff.add(typeWithValue);
            } else if (!file1.containsKey(key) && file2.containsKey(key)) {
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
