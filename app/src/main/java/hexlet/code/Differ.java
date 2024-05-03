package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Path absolutePath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path absolutePath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        var fileFormat = filepath1.split("\\.")[1];
        String data1 = Files.readString(absolutePath1);
        String data2 = Files.readString(absolutePath2);
        var diff = Tree.generateTree(data1, data2, fileFormat);
        return Formatter.chooseFormatter(diff, format);
    }
}
