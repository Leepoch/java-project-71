package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.HashMap;

public class FormatterStylish {
    public static String formatter(ArrayList<HashMap<String, Object>> diff) {
        StringBuilder diffInStylish = new StringBuilder();
        diffInStylish.append("{\n");
        for (var field : diff) {
            var key = String.valueOf(field.get("key"));
            var value = field.get("value");
            switch (String.valueOf(field.get("type"))) {
                case "notChanged":
                    diffInStylish.append(getField(" ", key, value));
                    break;
                case "changedFrom", "deleted":
                    diffInStylish.append(getField("-", key, value));
                    break;
                case "changedTo", "added":
                    diffInStylish.append(getField("+", key, value));
                    break;
                default:
                    break;
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
