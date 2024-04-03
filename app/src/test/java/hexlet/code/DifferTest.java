package hexlet.code;

import org.junit.jupiter.api.Test;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.*;

public class DifferTest {
    @Test
    public void testSum() throws Exception {
        var expected = 5;
        var filepath1 = "resources/file1.json";
        var filepath2 = "resources/file2.json";
        var actual = generate(filepath1, filepath2);
        assertEquals(expected, actual);
    }
}
