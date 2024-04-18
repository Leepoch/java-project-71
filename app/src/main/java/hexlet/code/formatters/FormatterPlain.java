package hexlet.code.formatters;


import java.util.ArrayList;
import java.util.HashMap;

public class FormatterPlain {
    public static String formatter(ArrayList<HashMap<String, Object>> diff) {
        StringBuilder diffInStylish = new StringBuilder();
        for (var field : diff) {
            var key = String.valueOf(field.get("key"));
            var value = field.get("value");
            if (value != null) {
                var valueType = value.getClass().getSimpleName();
                if (valueType.equals("LinkedHashMap") || valueType.equals("ArrayList")) {
                    value = "[complex value]";
                } else if (valueType.equals("String")) {
                    value = "'" + value + "'";
                }
            } else {
                value = String.valueOf(value);
            }
            switch (String.valueOf(field.get("type"))) {
                case "changedFrom":
                    diffInStylish.append("Property '")
                            .append(key)
                            .append("' was updated. From ")
                            .append(value)
                            .append(" to ");
                    break;
                case "changedTo":
                    diffInStylish.append(value)
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
}
