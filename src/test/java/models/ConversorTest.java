package models;

import org.junit.Before;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ConversorTest {

    Conversor sC;
    Conversor dC;

    @Before
    public void setUp() {
        sC = new Conversor(PrecisionEnum.SIMPLE);
        dC = new Conversor(PrecisionEnum.DOUBLE);
    }

    @org.junit.Test
    public void convertSimpleFormat() {

    }

    @org.junit.Test
    public void printIEEEFormat() {

    }

    @org.junit.Test
    public void calculateExponent() {

    }

    @org.junit.Test
    public void calculateNegativeShifts() {
    }

    @org.junit.Test
    public void conformateMantisa() {
    }

    @org.junit.Test
    public void getWholePartBits() {
        int[] testCases = new int[]{
                0, 1, 2, Integer.MAX_VALUE, 1234123, 9999, 992939129
        };
        //Test Basic cases
        for (int test: testCases) {
            assertEquals(Integer.toBinaryString(test), arrayToString(sC.getWholePartBits(test)));
        }
        //Test for negative values
        assertThrows(IllegalArgumentException.class,() -> sC.getWholePartBits(-1));
    }

    @org.junit.Test
    public void convertPartDecimal() {
    }

    private static String arrayToString(List<Integer> integers) {
        return integers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }
}