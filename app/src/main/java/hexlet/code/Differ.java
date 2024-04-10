package hexlet.code;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static List<FieldData> getDiff(Map<String, Object> fileData1, Map<String, Object> fileData2) {
        Map<String, Object> mergedData = new HashMap<>(fileData1);
        mergedData.putAll(fileData2);
        Map<String, Object> sortedMergedData = new TreeMap<>(mergedData);
        var diff = new ArrayList<FieldData>();
        sortedMergedData.forEach((key, value) -> {
            if (fileData1.containsKey(key) && fileData2.containsKey(key)) {
                var valueFieldFile1 = fileData1.get(key).toString();
                var valueFieldFile2 = fileData2.get(key).toString(); // проверить чтобы не обращаться по несуществующему ключу
                if (valueFieldFile1.equals(valueFieldFile2)) {
                    diff.add(new FieldData("notChanged", key, value.toString()));
                } else {
                    diff.add(new FieldData("changedFrom", key, value.toString()));
                    diff.add(new FieldData("changedTo", key, value.toString()));
                }
            } else if (fileData1.containsKey(key) && !fileData2.containsKey(key)) {
                diff.add(new FieldData("deleted", key, value.toString()));
            } else if (!fileData1.containsKey(key) && fileData2.containsKey(key)) {
                diff.add(new FieldData("added", key, value.toString()));
            }
        });
        return diff;
    }
}
