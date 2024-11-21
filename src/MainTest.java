import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

//    @ParameterizedTest
//    @CsvSource({
//            "true,20",
//            "true,12"
//    })
//    void testIsNumeric(boolean expected, String input) {
//        assertEquals(expected, Main.isNumeric(input));
//    }

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

}