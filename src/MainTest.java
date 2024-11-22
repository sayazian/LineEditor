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

    @Test
    void insertTheNewLines() {
        String[] input = {"insertion 1", "insertion 2"};
        String[] expected = {"line 1", "insertion 1", "insertion 2", "line 2", "line 3", "line 4", "line 5"};
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

    @Test
    void replaceTheNewLines() {
        String[] input = {"replacing 1", "replacing 2"};
        String[] expected = {"line 1", "line 2", "replacing 1", "replacing 2", "line 5"};
        replaceTheNewLines(input, expected);
    }

    void replaceTheNewLines(String[] input, String[] expected) {
        int lineNumber = 3;
        String[] fileLines = new String[5];
        String[] constant = {"line 1", "line 2", "line 3", "line 4", "line 5"};
        System.arraycopy(constant, 0, fileLines,0,5);
        Main.replaceWithNewLines(fileLines, input, lineNumber);
        assertArrayEquals(expected, fileLines);
    }
   @Test
    void deleteLines1() {
        int currentLineNumber = 2;
        int numberOfLinesToDelete = 1;
        String[] fileLines = {"line 1", "line 2", "line 3", "line 4", "line 5"};
        String[] expected = {"line 1", "line 3", "line 4", "line 5", null};
        deleteTheLines(fileLines, expected, currentLineNumber, numberOfLinesToDelete);
    }

   @Test
    void deleteLines2() {
        int currentLineNumber = 5;
        int numberOfLinesToDelete = 1;
        String[] fileLines = {"line 1", "line 2", "line 3", "line 4", "line 5"};
        String[] expected = {"line 1", "line 2", "line 3", "line 4", null};
        deleteTheLines(fileLines, expected, currentLineNumber, numberOfLinesToDelete);
    }

    @Test
    void deleteLines3() {
        int currentLineNumber = 1;
        int numberOfLinesToDelete = 5;
        String[] fileLines = {"line 1", "line 2", "line 3", "line 4", "line 5"};
        String[] expected = {null, null, null, null, null};
        deleteTheLines(fileLines, expected, currentLineNumber, numberOfLinesToDelete);
    }

    @Test
    void deleteLines4() {
        int currentLineNumber = 1;
        int numberOfLinesToDelete = 0;
        String[] fileLines = {"line 1", "line 2", "line 3", "line 4", "line 5"};
        String[] expected = {"line 1", "line 2", "line 3", "line 4", "line 5"};
        deleteTheLines(fileLines, expected, currentLineNumber, numberOfLinesToDelete);
    }

    void deleteTheLines(String[] fileLines, String[] expected, int currentLineNumber, int numberOfLinesToDelete) {
        Main.runDeleteCommand(fileLines,currentLineNumber, numberOfLinesToDelete);
        assertArrayEquals(expected, fileLines);
    }
}
