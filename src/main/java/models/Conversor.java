package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Conversor {

    private final PrecisionEnum precision;
    private ArrayList<Integer> mantisa = new ArrayList<>();
    private ArrayList<Integer> exponent = new ArrayList<>();
    private String hexadecimal="";
    private int signo = 0;
    private HashMap<Integer,String> valuesHexadecimals = new HashMap<>();
    private double decimalValue = 0;

    public Conversor(PrecisionEnum precision) {
        this.precision = precision;
    }

    public void initValuesMap(){
        valuesHexadecimals.put(10,"A");
        valuesHexadecimals.put(11,"B");
        valuesHexadecimals.put(12,"C");
        valuesHexadecimals.put(13,"D");
        valuesHexadecimals.put(14,"E");
        valuesHexadecimals.put(15,"F");
    }
    public void convertToIEEE(double numberUser) {
        initValuesMap();
        int wholePart = (int) numberUser;//Obtiene parte entera
        double decimalPart = Math.abs(numberUser - wholePart);//Obtiene la parte decimal
        ArrayList<Integer> binInteger = getWholePartBits(wholePart);
        ArrayList<Integer> binDecimal = getDecimalPartBits(
            decimalPart, precision.bitsMantisa - (binInteger.size() - 1)
            );
        signo = (numberUser >= 0) ? 0 : 1;

        if (wholePart != 0) {
            this.exponent = calculateExponent(binInteger.size() - 1);
            mantisa = conformateMantisa(binInteger, binDecimal);
            convertToHexadecimal();
            return;
        }

        int negativeShifts = calculateNegativeShifts(binDecimal);
        if (negativeShifts > 0) {
            binDecimal.addAll(getDecimalPartBits(decimalValue, negativeShifts));
            binDecimal=new ArrayList<>(binDecimal.subList(negativeShifts, binDecimal.size())); 
        }
        this.exponent = calculateExponent(-negativeShifts);
        mantisa = conformateMantisa(binInteger, binDecimal);
        convertToHexadecimal();
    }

    public void convertToHexadecimal(){
        String responseHexadecimal ="";
        String valueBin=""+signo;
        System.out.println(valueBin);
        for (int i = 0; i < exponent.size(); i++) {
            valueBin+=exponent.get(i);
        }
        for (int i = 0; i < mantisa.size(); i++) {
            valueBin+=mantisa.get(i);
        }
        for (int i = 0; i < valueBin.length(); i+=4) {
            String subStrings= valueBin.substring(i,i+4);
            int totalNible=0;
            for (int j = 0; j < subStrings.length(); j++) {
                totalNible+= Double.parseDouble(subStrings.substring(subStrings.length()-1-j,subStrings.length()-j))*Math.pow(2.0,(double) j);
            }
            if(totalNible>9){
                responseHexadecimal+=""+valuesHexadecimals.get(totalNible);
            }else{
                responseHexadecimal+=""+totalNible;
            }
        }
        hexadecimal=responseHexadecimal;
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
        //if (numberUser < 0) throw new IllegalArgumentException("Negative Value");
        numberUser=Math.abs(numberUser);
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
