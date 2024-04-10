package hexlet.code;

import hexlet.code.formatters.FormatterPlain;
import hexlet.code.formatters.FormatterStylish;

import java.util.Map;
import java.util.HashMap;

public class Formatter {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
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
        var diffInFormat = "";
        if (format.equals("stylish")) {
            diffInFormat = FormatterStylish.formater(diff);
        } else {
            diffInFormat = FormatterPlain.formatter(diff);
        }
        return diffInFormat;
    }
}
