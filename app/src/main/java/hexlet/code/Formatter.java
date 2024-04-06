package hexlet.code;

import java.util.*;

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
            if (field.getState().equals("notChanged")) {
                diffInStylish.append(getField(" ", field.getKey(), field.getValue()));
            }
            if (field.getState().equals("changedFrom")) {
                diffInStylish.append(getField("-", field.getKey(), field.getValue()));
            }
            if (field.getState().equals("changedTo")) {
                diffInStylish.append(getField("+", field.getKey(), field.getValue()));
            }
            if (field.getState().equals("deleted")) {
                diffInStylish.append(getField("-", field.getKey(), field.getValue()));
            }
            if (field.getState().equals("added")) {
                diffInStylish.append(getField("+", field.getKey(), field.getValue()));
            }
        }
        return diffInStylish.toString();
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
