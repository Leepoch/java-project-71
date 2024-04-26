package hexlet.code;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var diff = Tree.generateTree(filepath1, filepath2);
        return Formatter.chooseFormatter(diff, format);
    }
}
