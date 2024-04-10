package hexlet.code.formatters;

import hexlet.code.FieldData;

import java.util.List;

public class FormatterStylish {
    public static String formater(List<FieldData> diff) {
        StringBuilder diffInStylish = new StringBuilder();
        diffInStylish.append("{\n");
        for (var field : diff) {
            var state = field.getState();
            var key = field.getKey();
            var value = String.valueOf(field.getValue());
            switch (state) {
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
