package hexlet.code;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Formatter {
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
        var diff = Differ.getDiff(fileData1, fileData2);
        return formaterStylish(diff);
    }

    public static String formaterStylish(List<FieldData> diff) {
        StringBuilder diffInStylish = new StringBuilder();
        diffInStylish.append("{\n");
        for (var field : diff) {
            var state = field.getState();
            var key = field.getKey();
            var value = String.valueOf(field.getValue());
            if (state.equals("notChanged")) {
                diffInStylish.append(getField(" ", key, value));
            }
            if (state.equals("changedFrom")) {
                diffInStylish.append(getField("-", key, value));
            }
            if (state.equals("changedTo")) {
                diffInStylish.append(getField("+", key, value));
            }
            if (state.equals("deleted")) {
                diffInStylish.append(getField("-", key, value));
            }
            if (state.equals("added")) {
                diffInStylish.append(getField("+", key, value));
            }
        }
        diffInStylish.append("}");
        return diffInStylish.toString();
    }

    public static String getField(String sign, String key, Object value) {
        return "  "
                + sign
                + " "
                + key
                + ": "
                + value
                + "\n";
    }
}
