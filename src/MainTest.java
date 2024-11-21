import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @ParameterizedTest
    @ValueSource(strings = {"20", "12"})
    void testIsNumericPasses(String input) {
        assertTrue(Main.isNumeric(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"BADbadBAD", "??"})
    void testIsNumericFails(String input) {
        assertFalse(Main.isNumeric(input));
    }

//    @ParameterizedTest
//    @CsvSource({
//            "{\"line 1\", \"insertion 1\", \"insertion 2\",  \"line 2\", \"line 3\", \"line 4\", \"line 5\"},{\"insertion 1\", \"insertion 2\"}"
//    })
//    void insertTheNewLines(String[] input, String[] expected) {
//        int lineNumber = 2;
//        String[] fileLines = {"line 1", "line 2", "line 3", "line 4", "line 5"};
//        Main.insertTheNewLines(fileLines, input, lineNumber);
//        assertEquals(expected, fileLines);
//    }
    @Test
    void insertTheNewLines() {
        String[] input = {"insertion 1", "insertion 2"};
        String[] expected = {"line 1", "line 2", "insertion 1", "insertion 2", "line 3", "line 4", "line 5"};
        insertTheNewLines(input, expected);
    }

    void insertTheNewLines(String[] input, String[] expected) {
        int lineNumber = 2;
        String[] fileLines = new String[7];
        String[] constant = {"line 1", "line 2", "line 3", "line 4", "line 5"};
        System.arraycopy(constant, 0, fileLines,0,5);
        Main.insertTheNewLines(fileLines, input, lineNumber);
        assertArrayEquals(expected, fileLines);
    }
}
