package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> fileData1 = Cli.jsonIntoMap(filepath1);
        Map<String, Object> fileData2 = Cli.jsonIntoMap(filepath2);
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
