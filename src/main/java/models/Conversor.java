package models;

import java.util.ArrayList;

public class Conversor {

    private PrecisionEnum precision;
    private ArrayList<Integer> mantisa = new ArrayList<>();
    private ArrayList<Integer> exponent = new ArrayList<>();
    private int signo = 0;
    private double decimalValue = 0;

    public Conversor(PrecisionEnum precision) {
        this.precision = precision;
    }


    public void convertSimpleFormat(double numberUser, int precision) {
        int partInt = (int) numberUser;
        double partDecimal = numberUser - partInt;
        ArrayList<Integer> binInteger = getWholePartBits(partInt);
        ArrayList<Integer> binDecimal = convertPartDecimal(partDecimal, 23 - (binInteger.size() - 1));
        signo = (numberUser > 0) ? 0 : 1;
        if (partInt == 0) {
            int negativeShifts = calculateNegativeShifts(binDecimal);
            if (negativeShifts > 0) {
                binDecimal.addAll(convertPartDecimal(decimalValue, negativeShifts));
            }
            this.exponent = calculateExponent(precision == 1 ? 8 : 11, -negativeShifts);
        } else {
            this.exponent = calculateExponent(precision == 1 ? 8 : 11, binInteger.size() - 1);
        }
        mantisa = conformateMantisa(binInteger, binDecimal);
    }

    public void printIEEEFormat() {
        System.out.println("signo: " + signo);
        System.out.println("exponent: " + exponent.toString());
        System.out.println("mantisa: " + mantisa.toString());
    }

    public ArrayList<Integer> calculateExponent(int precisionExponent, int shifts) {
        int exponentDecimal = (int) Math.pow(2.0, precisionExponent - 1) - 1 + shifts;
        ArrayList<Integer> exponent = getWholePartBits(exponentDecimal);
        //System.out.println(exponent.size()+"  "+precisionExponent);
        while (exponent.size() != precisionExponent) {
            exponent.add(0);
            //System.out.println("sdds");
        }
        return exponent;
    }

    public int calculateNegativeShifts(ArrayList<Integer> binDecimal) {
        int shift = 1;
        for (Integer integer : binDecimal) {
            if (integer != 0) {
                return shift;
            }
            shift++;
        }
        return shift;
    }

    public ArrayList<Integer> conformateMantisa(ArrayList<Integer> binInteger, ArrayList<Integer> binDecimal) {
        binInteger.addAll(binDecimal);
        while (binInteger.get(0) != 0) {
            binInteger.remove(0);
        }
        return binInteger;
    }

    /**
     * Calcula la representación de un número entero a binario
     * @param numberUser entero a representar en binario
     * @return un array con los bits del número
     * @throws IllegalArgumentException si numberUser es negativo
     */
    public ArrayList<Integer> getWholePartBits(int numberUser) {
        if (numberUser < 0) throw new IllegalArgumentException("Negative Value");
        ArrayList<Integer> binInt = new ArrayList<>();
        if (numberUser == 0) {
            binInt.add(numberUser);
            return binInt;
        }
        while (numberUser > 0) {
            binInt.add(0,numberUser % 2);
            numberUser = numberUser / 2;
        }
        return binInt;
    }

    public ArrayList<Integer> convertPartDecimal(double decimal, int totalPresicion) {
        ArrayList<Integer> binDecimal = new ArrayList<>();
        while (totalPresicion >= 0) {
            totalPresicion--;
            decimal *= 2;
            if (decimal > 0) {
                binDecimal.add(1);
                decimal = decimal - 1;
            } else {
                binDecimal.add(1);
            }
        }
        decimalValue = decimal;
        return binDecimal;
    }
}
