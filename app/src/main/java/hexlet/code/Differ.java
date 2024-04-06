package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        var fileFormat = filepath1.split("\\.")[1];
        Map<String, Object> fileData1 = new HashMap<>();
        Map<String, Object> fileData2 = new HashMap<>();
        if (fileFormat.equals("json")) {
            fileData1.putAll(Parser.jsonIntoMap(filepath1));
            fileData2.putAll(Parser.jsonIntoMap(filepath2));
        } else if (fileFormat.equals("yml")) {
            fileData1.putAll(Parser.ymlIntoMap(filepath1));
            fileData2.putAll(Parser.ymlIntoMap(filepath2));
        } else {
            throw new Exception("Invalid format");
        }
        return getDiff(fileData1, fileData2);
    }
    public static String getDiff(Map<String, Object> fileData1, Map<String, Object> fileData2) {
        StringBuilder diffResult = new StringBuilder();
        Map<String, Object> mergedData = new HashMap<>(fileData1);
        mergedData.putAll(fileData2);
        Map<String, Object> sortedMergedData = new TreeMap<>(mergedData);
        diffResult.append("{\n");
        sortedMergedData.forEach((key, value) -> {
            var valueFile1 = fileData1.get(key);
            var valueFile2 = fileData2.get(key);
            if (fileData1.containsKey(key) && fileData2.containsKey(key)) {
                if (valueFile1.equals(valueFile2)) {
                    diffResult.append(getField(" ", key, value));
                } else {
                    diffResult.append(getField("-", key, valueFile1))
                            .append(getField("+", key, valueFile2));
                }
            } else if (fileData1.containsKey(key) && !fileData2.containsKey(key)) {
                diffResult.append(getField("-", key, valueFile1));
            } else if (!fileData1.containsKey(key) && fileData2.containsKey(key)) {
                diffResult.append(getField("+", key, valueFile2));
            }
        });
        diffResult.append("}");
        return diffResult.toString();
    }
    public static String getField(String sign, String key, Object value) {
        StringBuilder field = new StringBuilder();
        return field.append("  ")
                .append(sign)
                .append(" ")
                .append(key)
                .append(": ")
                .append(value)
                .append("\n")
                .toString();
    }
}
