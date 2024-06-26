package hexlet.code;

import hexlet.code.formatters.FormatterJson;
import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Formatter {
    public static String chooseFormatter(
            ArrayList<HashMap<String, Object>> diff,
            String format
    ) throws IOException {
        return switch (format) {
            case "stylish" -> FormatterStylish.formatter(diff);
            case "plain" -> FormatterPlain.formatter(diff);
            case "json" -> FormatterJson.formatter(diff);
            default -> throw new IOException();
        };
    }
}
