package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;

import static hexlet.code.Formatter.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatterTest {
    private final String EXPECTED_VALUE = "{\n" +
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
    @Test
    public void testDiffJson() throws Exception {
        String resourceName1 = "file1.json";
        String resourceName2 = "file2.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file1 = new File(classLoader.getResource(resourceName1).getFile());
        File file2 = new File(classLoader.getResource(resourceName2).getFile());
        String absolutePath1 = file1.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        var actual = generate(absolutePath1, absolutePath2);
        assertEquals(EXPECTED_VALUE, actual);
    }

    @Test
    public void testDiffYml() throws Exception {
        String resourceNameYml1 = "file1.yml";
        String resourceNameYml2 = "file2.yml";
        ClassLoader classLoader = getClass().getClassLoader();
        File file1 = new File(classLoader.getResource(resourceNameYml1).getFile());
        File file2 = new File(classLoader.getResource(resourceNameYml2).getFile());
        String absolutePath1 = file1.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        var actual = generate(absolutePath1, absolutePath2);
        assertEquals(EXPECTED_VALUE, actual);
    }
}
