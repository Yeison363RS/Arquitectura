package models;

import util.NotationConverter;

import java.util.ArrayList;

public class IEEEConverter {

    private final PrecisionEnum precision;
    private ArrayList<Integer> mantisa = new ArrayList<>();
    private ArrayList<Integer> exponent = new ArrayList<>();
    private String hexadecimal="";
    private int signo = 0;
    private double decimalValue = 0;

    public IEEEConverter(PrecisionEnum precision) {
        this.precision = precision;
    }

    public void convertToIEEE(double numberUser) {
        int wholePart = (int) numberUser;//Obtiene parte entera
        double decimalPart = Math.abs(numberUser - wholePart);//Obtiene la parte decimal
        ArrayList<Integer> binInteger = NotationConverter.getWholePartBits(wholePart);
        ArrayList<Integer> binDecimal = getDecimalPartBits(
            decimalPart, precision.bitsMantisa - (binInteger.size() - 1)
            );
        signo = (numberUser >= 0) ? 0 : 1;

        if (wholePart != 0) {
            this.exponent = calculateExponent(binInteger.size() - 1);
            mantisa = conformateMantisa(binInteger, binDecimal);
            this.hexadecimal = convertToHexadecimal(signo,exponent, mantisa);
            return;
        }

        int negativeShifts = calculateNegativeShifts(binDecimal);
        if (negativeShifts > 0) {
            binDecimal.addAll(getDecimalPartBits(decimalValue, negativeShifts));
            binDecimal=new ArrayList<>(binDecimal.subList(negativeShifts, binDecimal.size())); 
        }
        this.exponent = calculateExponent(-negativeShifts);
        mantisa = conformateMantisa(binInteger, binDecimal);
        this.hexadecimal = convertToHexadecimal(signo,exponent, mantisa);
    }

    public String convertToHexadecimal(int sign, ArrayList<Integer> exp, ArrayList<Integer> man){
        ArrayList<Integer> pivot = new ArrayList<>();
        pivot.addAll(exp);
        pivot.addAll(man);
        String binaryString = sign + NotationConverter.listToString(pivot);
        return NotationConverter.binaryStringToHexadecimal(binaryString);
    }
    public void printIEEEFormat() {
        System.out.println("signo: " + signo);
        System.out.println("exponent: " + exponent.toString());
        System.out.println("mantisa: " + mantisa.toString());
        System.out.println("hexadecimal: "+this.hexadecimal);
    }

    public ArrayList<Integer> calculateExponent(int shifts) {
        int exponentDecimal = (int) Math.pow(2.0, precision.bitsExponent - 1) - 1 + shifts;
        if(shifts==-24){
            exponentDecimal=0;
        }
        ArrayList<Integer> exponent = NotationConverter.getWholePartBits(exponentDecimal);
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
        binInteger.remove(0);
        return binInteger;
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
