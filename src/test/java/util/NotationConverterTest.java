package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotationConverterTest {

    @Test
    public void getWholePartBits() {
        int[] testCases = new int[]{
                0, 1, 2, Integer.MAX_VALUE, 1234123, 9999, 992939129
        };
        StringBuilder bd = new StringBuilder();
        //Test Basic cases
        for (int test : testCases) {
            assertEquals(Integer.toBinaryString(test), NotationConverter.listToString(NotationConverter.getWholePartBits(test,bd)));
        }
        //Test for negative values
        //assertThrows(IllegalArgumentException.class, () -> sC.getWholePartBits(-1));
    }

    @Test
    void binaryStringToHexadecimal() {
        String[][] tests = new String[][]{
                {"001","1"},
                {"100010101001010","454A"},
                {"1000000000000000000000000000000000000000000000000000000000000001","8000000000000001"},
                {"10000000100000000100011000010010101010000000000000000000000001","20201184AA000001"},
                {"000000000000000000000","000000"},
                {"",""}
        };
        for (String[] test: tests) {
            assertEquals(test[1],NotationConverter.binaryStringToHexadecimal(test[0]));
        }
        assertThrows(IllegalArgumentException.class, () -> NotationConverter.binaryStringToHexadecimal("12"));
        assertThrows(IllegalArgumentException.class, () -> NotationConverter.binaryStringToHexadecimal("1 "));
    }
}