package models;

import org.junit.Before;

import java.util.ArrayList;
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
    public void calculateIEEE() {
        //assertEquals("", );
    }

    @org.junit.Test
    public void printIEEEFormat() {
        sC.convertSimpleFormat(-0.75);
        sC.printIEEEFormat();
    }

    @org.junit.Test
    public void calculateExponent() {

    }

    @org.junit.Test
    public void calculateNegativeShifts() {
    }

    @org.junit.Test
    public void conformateMantisa() {
        String[] tests = new String[]{
                "0000111100001111000011110000111"
        };
        for (String test : tests) {
            System.out.println(getInts(test.substring(0, PrecisionEnum.SIMPLE.bitsExponent)));
            System.out.println(getInts(test.substring(PrecisionEnum.SIMPLE.bitsExponent)));
            assertEquals(
                    test,
                    arrayToString(sC.conformateMantisa(
                            getInts(test.substring(0, PrecisionEnum.SIMPLE.bitsExponent)),
                            getInts(test.substring(PrecisionEnum.SIMPLE.bitsMantisa))
                    )));
        }
    }

    @org.junit.Test
    public void getWholePartBits() {
        int[] testCases = new int[]{
                0, 1, 2, Integer.MAX_VALUE, 1234123, 9999, 992939129
        };
        //Test Basic cases
        for (int test : testCases) {
            assertEquals(Integer.toBinaryString(test), arrayToString(sC.getWholePartBits(test)));
        }
        //Test for negative values
        assertThrows(IllegalArgumentException.class, () -> sC.getWholePartBits(-1));
    }


    @org.junit.Test
    public void getDecimalPartBits() {
        double[] tests = new double[]{
                0.5, 0.75, 0.875, 0.9375, 0.96875, 0.984375, 0.9921875,
                0.99609375, 0.998046875, 0.9990234375, 0.99951171875,
                0.999755859375, 0.9998779296875, 0.99993896484375,
                0.999969482421875, 0.9999847412109375, 0.9999923706054688,
                0.9999961853027344, 0.9999980926513672, 0.9999990463256836,
                0.9999995231628418, 0.9999997615814209, 0.9999998807907104
        };
        String value = "000000000000000000000000";
        //Test for 100000000000000000000000, 110000000000000000000000, 111000000000000000000000, ...
        for (double test : tests) {
            value = value.replaceFirst("0", "1");
            assertEquals(value,
                    arrayToString(sC.getDecimalPartBits(test, 24)));
        }
        tests = new double[]{
                1.1920928955078125e-07, 3.5762786865234375e-07, 8.344650268554688e-07,
                1.7881393432617188e-06, 3.6954879760742188e-06, 7.510185241699219e-06,
                1.5139579772949219e-05, 3.039836883544922e-05, 6.091594696044922e-05,
                0.00012195110321044922, 0.00024402141571044922, 0.0004881620407104492,
                0.0009764432907104492, 0.0019530057907104492, 0.0039061307907104492,
                0.007812380790710449, 0.01562488079071045, 0.03124988079071045,
                0.06249988079071045, 0.12499988079071045, 0.24999988079071045,
                0.49999988079071045, 0.9999998807907104};
        String value1 = "111111111111111111111110";
        //Test for 111111111111111111111110, 111111111111111111111100, 111111111111111111111000, ...
        for (int i = tests.length-1; i >= 0; i--) {
            assertEquals(value1,
                    arrayToString(sC.getDecimalPartBits(tests[i], 24)));
            value1 = value1.replaceFirst("1", "0");
        }

        assertEquals("100000000000000000000010",
                arrayToString(sC.getDecimalPartBits(0.5000001192092896, 24))
        );
    }

    private static String arrayToString(List<Integer> integers) {
        return integers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    private static ArrayList<Integer> getInts(String number) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (char c : number.toCharArray()) {
            numbers.add(c - 48);
        }
        return numbers;
    }
}