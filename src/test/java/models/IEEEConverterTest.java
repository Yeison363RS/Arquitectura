package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.NotationConverter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IEEEConverterTest {

    IEEEConverter sC;
    IEEEConverter dC;

    @BeforeEach
    void setUp() {
        sC = new IEEEConverter(PrecisionEnum.SIMPLE);
        dC = new IEEEConverter(PrecisionEnum.DOUBLE);
    }

    @Test
    public void convertToIEEE() {
        String[][] tests = new String[][]{
                //{numero, signo, exponente, mantisa}
                {"-0.75","1","01111110","10000000000000000000000"},
                {"-0.7521","1","01111110","10000001000100110100000"},
                {"-2.25","1","10000000","00100000000000000000000"},
                {"-0","0","00000000","00000000000000000000000"}
        };
        for (String[] test: tests) {
            double num = Double.parseDouble(test[0]);
            sC.convertToIEEE(num);
            assertEquals(Integer.parseInt(test[1]),sC.getSign(),"caso: " + test[0]+" SIGNO");
            assertEquals(test[2],sC.getExponent(),"caso: " +test[0]+" EXPONENTE");
            assertEquals(test[3],sC.getMantisa(),"caso: " +test[0]+" MANTISA");
        }

        tests = new String[][]{
                //{numero, signo, exponente, mantisa}
                {"-0.75","1","01111111110","1000000100010011010000000100111010100100101010001100"}//,
                //{"-0.7521","1","01111110","10000001000100110100000"},
                //{"-2.25","1","10000000","00100000000000000000000"},
                //{"-0","0","00000000","00000000000000000000000"}
        };
        for (String[] test: tests) {
            double num = Double.parseDouble(test[0]);
            sC.convertToIEEE(num);
            assertEquals(Integer.parseInt(test[1]),dC.getSign(),"caso: " + test[0]+" SIGNO");
            assertEquals(getInts(test[2]),dC.getExponent(),"caso: " +test[0]+" EXPONENTE");
            assertEquals(getInts(test[3]),dC.getMantisa(),"caso: " +test[0]+" MANTISA");
        }
    }

    @Test
    public void printIEEEFormat() {
        double[] tests = new double[]{
                -0.75,
                -0.7521,
                -0
        };
        for (double test: tests) {
            System.out.println(test);
            sC.convertToIEEE(test);
            sC.printIEEEFormat();
        }
    }

    @Test
    public void calculateExponent() {

    }

    @Test
    public void calculateNegativeShifts() {
    }

    @Test
    public void conformateMantisa() {
        String[][] tests = new String[][]{
                {"100100", "0", "100100"},
                {"00000000000000000000001", "1", "00000000000000000000001"},
                {"11100001101010101", "0", "11100001101010101"},
                {"00000000000000000000001", "1", "00000000000000000000001"}
        };
        for (String[] test : tests) {
            assertEquals(
                    test[0],
                    NotationConverter.listToString(sC.conformateMantisa(getInts(test[1]), getInts(test[2])
                    )));
        }
    }

    @Test
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
                    NotationConverter.listToString(sC.getDecimalPartBits(test, 24)));
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
        for (int i = tests.length - 1; i >= 0; i--) {
            assertEquals(value1,
                    NotationConverter.listToString(sC.getDecimalPartBits(tests[i], 24)));
            value1 = value1.replaceFirst("1", "0");
        }

        assertEquals("100000000000000000000010",
                NotationConverter.listToString(sC.getDecimalPartBits(0.5000001192092896, 24))
        );
    }

    private static ArrayList<Integer> getInts(String number) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (char c : number.toCharArray()) {
            numbers.add(c - 48);
        }
        return numbers;
    }
}