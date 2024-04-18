package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;

import static hexlet.code.Formatter.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatterTest {
    private final String EXPECTED_VALUE_STYLISH = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";
    private final String EXPECTED_VALUE_PLAIN = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";
    private final String EXPECTED_VALUE_JSON = """
            {"type":"notChanged","value":["a","b","c"],"key":"chars1"}
            {"type":"changedFrom","value":["d","e","f"],"key":"chars2"}
            {"type":"changedTo","value":false,"key":"chars2"}
            {"type":"changedFrom","value":false,"key":"checked"}
            {"type":"changedTo","value":true,"key":"checked"}
            {"type":"changedFrom","value":null,"key":"default"}
            {"type":"changedTo","value":["value1","value2"],"key":"default"}
            {"type":"changedFrom","value":45,"key":"id"}
            {"type":"changedTo","value":null,"key":"id"}
            {"type":"deleted","value":"value1","key":"key1"}
            {"type":"added","value":"value2","key":"key2"}
            {"type":"notChanged","value":[1,2,3,4],"key":"numbers1"}
            {"type":"changedFrom","value":[2,3,4,5],"key":"numbers2"}
            {"type":"changedTo","value":[22,33,44,55],"key":"numbers2"}
            {"type":"deleted","value":[3,4,5],"key":"numbers3"}
            {"type":"added","value":[4,5,6],"key":"numbers4"}
            {"type":"added","value":{"nestedKey":"value","isNested":true},"key":"obj1"}
            {"type":"changedFrom","value":"Some value","key":"setting1"}
            {"type":"changedTo","value":"Another value","key":"setting1"}
            {"type":"changedFrom","value":200,"key":"setting2"}
            {"type":"changedTo","value":300,"key":"setting2"}
            {"type":"changedFrom","value":true,"key":"setting3"}
            {"type":"changedTo","value":"none","key":"setting3"}      
            """;
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
    @Test
    public void testDiffJsonInJson() throws Exception {
        String resourceNameYml1 = "file1.yml";
        String resourceNameYml2 = "file2.yml";
        ClassLoader classLoader = getClass().getClassLoader();
        File file1 = new File(classLoader.getResource(resourceNameYml1).getFile());
        File file2 = new File(classLoader.getResource(resourceNameYml2).getFile());
        String absolutePath1 = file1.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        var actual = generate(absolutePath1, absolutePath2, "json");
        assertEquals(EXPECTED_VALUE_JSON, actual);
    }
}
