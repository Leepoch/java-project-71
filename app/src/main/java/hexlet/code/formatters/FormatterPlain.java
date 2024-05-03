package hexlet.code.formatters;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormatterPlain {
    public static String formatter(ArrayList<HashMap<String, Object>> diff) {
        StringBuilder diffInStylish = new StringBuilder();
        for (var field : diff) {
            var key = String.valueOf(field.get("key"));
            var value = stringify(field.get("value"));
            switch (String.valueOf(field.get("type"))) {
                case "changed":
                    var value1 = stringify(field.get("value1"));
                    var value2 = stringify(field.get("value2"));
                    diffInStylish.append("Property '")
                            .append(key)
                            .append("' was updated. From ")
                            .append(value1)
                            .append(" to ")
                            .append(value2)
                            .append("\n");
                    break;
                case "deleted":
                    diffInStylish.append("Property '")
                            .append(key)
                            .append("' was removed")
                            .append("\n");
                    break;
                case "added":
                    diffInStylish.append("Property '")
                            .append(key)
                            .append("' was added with value: ")
                            .append(value)
                            .append("\n");
                    break;
                default:
                    break;
            }
        }
        return diffInStylish.toString().trim();
    }

    public static Object stringify(Object value) {
        var result = value;
        if (value != null) {
            if (value instanceof List || value instanceof Map) {
                result = "[complex value]";
            } else if (value instanceof String) {
                result = "'" + value + "'";
            }
        } else {
            result = String.valueOf(value);
        }
        return result;
    }
}
