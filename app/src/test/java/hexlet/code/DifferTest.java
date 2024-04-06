package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testDiffJson() throws Exception {
        var expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";

        String resourceName1 = "file1.json";
        String resourceName2 = "file2.json";
        ClassLoader classLoader = getClass().getClassLoader();
        File file1 = new File(classLoader.getResource(resourceName1).getFile());
        File file2 = new File(classLoader.getResource(resourceName2).getFile());
        String absolutePath1 = file1.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        var actual = generate(absolutePath1, absolutePath2);
        assertEquals(expected, actual);
    }

    @Test
    public void testDiffYml() throws Exception {
        var expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";

        String resourceNameYml1 = "file1.yml";
        String resourceNameYml2 = "file2.yml";
        ClassLoader classLoader = getClass().getClassLoader();
        File file1 = new File(classLoader.getResource(resourceNameYml1).getFile());
        File file2 = new File(classLoader.getResource(resourceNameYml2).getFile());
        String absolutePath1 = file1.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        var actual = generate(absolutePath1, absolutePath2);
        assertEquals(expected, actual);
    }
}
