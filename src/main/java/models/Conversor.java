package models;

import java.util.ArrayList;

public class Conversor {

    private final PrecisionEnum precision;
    private ArrayList<Integer> mantisa = new ArrayList<>();
    private ArrayList<Integer> exponent = new ArrayList<>();
    private int signo = 0;
    private double decimalValue = 0;

    public Conversor(PrecisionEnum precision) {
        this.precision = precision;
    }

    public void convertToIEEE(double numberUser) {
        int wholePart = (int) numberUser;//Obtiene parte entera
        double decimalPart = Math.abs(numberUser - wholePart);//Obtiene la parte decimal
        ArrayList<Integer> binInteger = getWholePartBits(wholePart);
        ArrayList<Integer> binDecimal = getDecimalPartBits(
                decimalPart, precision.bitsMantisa - (binInteger.size() - 1)
        );
        signo = (numberUser > 0) ? 0 : 1;

        if (wholePart != 0) {
            this.exponent = calculateExponent(binInteger.size() - 1);
            mantisa = conformateMantisa(binInteger, binDecimal);
            return;
        }

        int negativeShifts = calculateNegativeShifts(binDecimal);
        if (negativeShifts > 0) {
            binDecimal.addAll(getDecimalPartBits(decimalValue, negativeShifts));
        }
        this.exponent = calculateExponent(-negativeShifts);
        mantisa = conformateMantisa(binInteger, binDecimal);
    }

    public void printIEEEFormat() {
        System.out.println("signo: " + signo);
        System.out.println("exponent: " + exponent.toString());
        System.out.println("mantisa: " + mantisa.toString());
    }

    public ArrayList<Integer> calculateExponent(int shifts) {
        int exponentDecimal = (int) Math.pow(2.0, precision.bitsExponent - 1) - 1 + shifts;
        ArrayList<Integer> exponent = getWholePartBits(exponentDecimal);
        while (exponent.size() != precision.bitsExponent) {
            exponent.add(0,0);
        }
        return exponent;
    }

    public int calculateNegativeShifts(ArrayList<Integer> binDecimal) {
        int shift = 1;
        for (Integer integer : binDecimal) {
            if (integer == 1) {
                return shift;
            }
            shift++;
        }
        return shift;
    }

    public ArrayList<Integer> conformateMantisa(ArrayList<Integer> binInteger, ArrayList<Integer> binDecimal) {
        binInteger.addAll(binDecimal);
        if (!binInteger.contains(1) || binInteger.get(binInteger.size()-1) == 1){
            binInteger.clear();
            binInteger.add(0);
            return binDecimal;
        }
        while (binInteger.get(0) == 0) {
            binInteger.remove(0);
        }
        binInteger.remove(0);
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

    public ArrayList<Integer> getDecimalPartBits(double decimal, int totalPrecision) {
        ArrayList<Integer> binDecimal = new ArrayList<>();
        for (int i = 0; i < totalPrecision; i++) {
            decimal *= 2;
            if (decimal >= 1) {
                binDecimal.add(1);
                decimal--;
                continue;
            }
            binDecimal.add(0);
        }
        decimalValue = decimal;
        return binDecimal;
    }

    public ArrayList<Integer> getMantisa() {
        return mantisa;
    }

    public ArrayList<Integer> getExponent() {
        return exponent;
    }

    public int getSigno() {
        return signo;
    }
}
