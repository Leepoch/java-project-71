package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;

import static hexlet.code.Formatter.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatterTest {
    private final String EXPECTED_VALUE_STYLISH = "{\n" +
            "    chars1: [a, b, c]\n" +
            "  - chars2: [d, e, f]\n" +
            "  + chars2: false\n" +
            "  - checked: false\n" +
            "  + checked: true\n" +
            "  - default: null\n" +
            "  + default: [value1, value2]\n" +
            "  - id: 45\n" +
            "  + id: null\n" +
            "  - key1: value1\n" +
            "  + key2: value2\n" +
            "    numbers1: [1, 2, 3, 4]\n" +
            "  - numbers2: [2, 3, 4, 5]\n" +
            "  + numbers2: [22, 33, 44, 55]\n" +
            "  - numbers3: [3, 4, 5]\n" +
            "  + numbers4: [4, 5, 6]\n" +
            "  + obj1: {nestedKey=value, isNested=true}\n" +
            "  - setting1: Some value\n" +
            "  + setting1: Another value\n" +
            "  - setting2: 200\n" +
            "  + setting2: 300\n" +
            "  - setting3: true\n" +
            "  + setting3: none\n" +
            "}";
    private final String EXPECTED_VALUE_PLAIN = "Property 'chars2' was updated. From [complex value] to false\n" +
            "Property 'checked' was updated. From false to true\n" +
            "Property 'default' was updated. From null to [complex value]\n" +
            "Property 'id' was updated. From 45 to null\n" +
            "Property 'key1' was removed\n" +
            "Property 'key2' was added with value: 'value2'\n" +
            "Property 'numbers2' was updated. From [complex value] to [complex value]\n" +
            "Property 'numbers3' was removed\n" +
            "Property 'numbers4' was added with value: [complex value]\n" +
            "Property 'obj1' was added with value: [complex value]\n" +
            "Property 'setting1' was updated. From 'Some value' to 'Another value'\n" +
            "Property 'setting2' was updated. From 200 to 300\n" +
            "Property 'setting3' was updated. From true to 'none'";
    @Test
    public void testDiffJsonStylish() throws Exception {
        String resourceName1 = "file1.json";
        String resourceName2 = "file2.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file1 = new File(classLoader.getResource(resourceName1).getFile());
        File file2 = new File(classLoader.getResource(resourceName2).getFile());
        String absolutePath1 = file1.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        var actual = generate(absolutePath1, absolutePath2, "stylish");
        assertEquals(EXPECTED_VALUE_STYLISH, actual);
    }
    @Test
    public void testDiffYmlStylish() throws Exception {
        String resourceNameYml1 = "file1.yml";
        String resourceNameYml2 = "file2.yml";
        ClassLoader classLoader = getClass().getClassLoader();
        File file1 = new File(classLoader.getResource(resourceNameYml1).getFile());
        File file2 = new File(classLoader.getResource(resourceNameYml2).getFile());
        String absolutePath1 = file1.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        var actual = generate(absolutePath1, absolutePath2, "stylish");
        assertEquals(EXPECTED_VALUE_STYLISH, actual);
    }
    @Test
    public void testDiffJsonPlain() throws Exception {
        String resourceNameYml1 = "file1.yml";
        String resourceNameYml2 = "file2.yml";
        ClassLoader classLoader = getClass().getClassLoader();
        File file1 = new File(classLoader.getResource(resourceNameYml1).getFile());
        File file2 = new File(classLoader.getResource(resourceNameYml2).getFile());
        String absolutePath1 = file1.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        var actual = generate(absolutePath1, absolutePath2, "plain");
        assertEquals(EXPECTED_VALUE_PLAIN, actual);
    }
    @Test
    public void testDiffYmlPlain() throws Exception {
        String resourceNameYml1 = "file1.yml";
        String resourceNameYml2 = "file2.yml";
        ClassLoader classLoader = getClass().getClassLoader();
        File file1 = new File(classLoader.getResource(resourceNameYml1).getFile());
        File file2 = new File(classLoader.getResource(resourceNameYml2).getFile());
        String absolutePath1 = file1.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        var actual = generate(absolutePath1, absolutePath2, "plain");
        assertEquals(EXPECTED_VALUE_PLAIN, actual);
    }
}
