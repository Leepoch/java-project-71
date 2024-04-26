package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stringify {
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
