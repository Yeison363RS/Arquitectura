package models;

import util.NotationConverter;

import java.util.ArrayList;

public class IEEEConverter {

    private PrecisionEnum precision;
    private ArrayList<Integer> mantisa = new ArrayList<>();
    private ArrayList<Integer> exponent = new ArrayList<>();
    private String hexadecimal="";
    private int sign = 0;
    private double decimalValue = 0;
    public IEEEConverter(PrecisionEnum precision) {
        this.precision = precision;
    }

    public void convertToIEEE(double numberUser) {
        Procedure.INSTANCE.clear();
        int wholePart = (int) numberUser;//Obtiene parte entera
        double decimalPart = Math.abs(numberUser - wholePart);//Obtiene la parte decimal
        ArrayList<Integer> binInteger = NotationConverter.getWholePartBits(wholePart, Procedure.INSTANCE.step1WholeToBinary);
        ArrayList<Integer> binDecimal = getDecimalPartBits(
            decimalPart, precision.bitsMantisa - (binInteger.size() - 1)
            );
        sign = (numberUser >= 0) ? 0 : 1;

        if (wholePart != 0) {
            this.exponent = calculateExponent(binInteger.size() - 1);
            mantisa = conformateMantisa(binInteger, binDecimal);
            this.hexadecimal = convertToHexadecimal(sign,exponent, mantisa);
            Procedure.INSTANCE.setResultDecimalToBinary(
                    Procedure.INSTANCE.step2DecimalPartToBinary,
                    NotationConverter.listToString(binDecimal));

            Procedure.INSTANCE.setStep3JoinWholeAndDecimal(binInteger, binDecimal);
            return;
        }

        int negativeShifts = calculateNegativeShifts(binDecimal);
        if (negativeShifts > 0) {
            binDecimal.addAll(getDecimalPartBits(decimalValue, negativeShifts));
            binDecimal=new ArrayList<>(binDecimal.subList(negativeShifts, binDecimal.size())); 
        }

        Procedure.INSTANCE.setResultDecimalToBinary(
                Procedure.INSTANCE.step2DecimalPartToBinary,
                NotationConverter.listToString(binDecimal));

        Procedure.INSTANCE.setStep3JoinWholeAndDecimal(binInteger, binDecimal);
        this.exponent = calculateExponent(-negativeShifts);
        mantisa = conformateMantisa(binInteger, binDecimal);
        this.hexadecimal = convertToHexadecimal(sign,exponent, mantisa);
    }

    public String convertToHexadecimal(int sign, ArrayList<Integer> exp, ArrayList<Integer> man){
        ArrayList<Integer> pivot = new ArrayList<>(exp);
        pivot.addAll(man);
        String binaryString = sign + NotationConverter.listToString(pivot);
        return NotationConverter.binaryStringToHexadecimal(binaryString);
    }
    public void printIEEEFormat() {
        System.out.println("signo: " + sign);
        System.out.println("exponent: " + exponent.toString());
        System.out.println("mantisa: " + mantisa.toString());
        System.out.println("hexadecimal: "+this.hexadecimal);
    }

    public ArrayList<Integer> calculateExponent(int shifts) {
        Procedure.INSTANCE.step4Slipping = shifts;//Almacena el corrimiento en el procedimiento
        int exponentDecimal = (int) Math.pow(2.0, precision.bitsExponent - 1) - 1 + shifts;
        if(shifts==-24){
            exponentDecimal=0;
        }
        ArrayList<Integer> exponent = NotationConverter.getWholePartBits(exponentDecimal, Procedure.INSTANCE.step6ExpToBinary);
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
        ArrayList<Integer> pivot = new ArrayList<>(binInteger);
        pivot.addAll(binDecimal);
        pivot.remove(0);
        return pivot;
    }

    public ArrayList<Integer> getDecimalPartBits(double decimal, int totalPrecision) {
        ArrayList<Integer> binDecimal = new ArrayList<>();
        for (int i = 0; i < totalPrecision; i++) {
            if (decimal > 0){
                Procedure.INSTANCE.addStepDecimalToBinary(Procedure.INSTANCE.step2DecimalPartToBinary,
                        ""+(int)(decimal*2),decimal+"*2");
            }
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

    public String getMantisa() {
        return NotationConverter.listToString(mantisa);
    }

    public String getExponent() {
        return NotationConverter.listToString(exponent);
    }

    public int getSign() {
        return sign;
    }

    public void setPrecision(PrecisionEnum precision) {
        this.precision = precision;
    }

    public PrecisionEnum getPrecision() {
        return precision;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }
}
