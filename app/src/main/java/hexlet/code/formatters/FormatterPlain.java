package hexlet.code.formatters;

import hexlet.code.FieldData;

import java.util.List;

public class FormatterPlain {
    public static String formatter(List<FieldData> diff) {
        StringBuilder diffInStylish = new StringBuilder();
        for (var field : diff) {
            var state = field.getState();
            var key = field.getKey();
            var value = field.getValue();
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
            switch (state) {
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
