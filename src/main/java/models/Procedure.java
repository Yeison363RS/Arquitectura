package models;

import util.NotationConverter;

import java.util.ArrayList;

public class Procedure {

    public static final Procedure INSTANCE = new Procedure();

    //Corrimiento
    StringBuilder step1WholeToBinary = new StringBuilder();
    StringBuilder step2DecimalPartToBinary = new StringBuilder();
    String step3JoinWholeAndDecimal = "";
    int step4Slipping;
    String step5CalcExponent = "";
    StringBuilder step6ExpToBinary = new StringBuilder();
    String step7IEEENotation = "";
    String step8ToHex = "";

    public void addStepDecimalToBinary(StringBuilder builder, String remainder, String value){
        builder.append(String.format("\t%s|%s\n",remainder,value));
    }

    public void setResultDecimalToBinary(StringBuilder builder, String value){
        builder.append("\nResultado: ").append(value);
    }

    public void addStepToHex(String value, String hexValue){
        step8ToHex = String.format("\t%s\t%s\n",value,hexValue).concat(step8ToHex);
    }

    public void setStep3JoinWholeAndDecimal(ArrayList<Integer> wholePart, ArrayList<Integer> decimalPart){
        step3JoinWholeAndDecimal = NotationConverter.listToString(wholePart)+","+
                NotationConverter.listToString(decimalPart);
    }

    public void setStep5CalcExponent(PrecisionEnum precision){
        step5CalcExponent = String.format("%d + %d = %d",
                precision.exponentValue,
                step4Slipping,
                step4Slipping + precision.exponentValue
                );
    }

    public void setStep7IEEENotation(int sign, ArrayList<Integer> exp, ArrayList<Integer> man) {
        this.step7IEEENotation = sign + "  "+
            NotationConverter.listToString(exp) + "  "+
            NotationConverter.listToString(man);
    }

    public int getStep4Slipping() {
        return step4Slipping;
    }

    public String getStep1WholeToBinary() {
        return step1WholeToBinary.toString();
    }

    public String getStep2DecimalPartToBinary() {
        return step2DecimalPartToBinary.toString();
    }

    public String getStep3JoinWholeAndDecimal() {
        return step3JoinWholeAndDecimal;
    }

    public String getStep5CalcExponent() {
        return step5CalcExponent;
    }

    public String getStep6ExpToBinary() {
        return step6ExpToBinary.toString();
    }

    public String getStep7IEEENotation() {
        return step7IEEENotation;
    }

    public String getStep8ToHex() {
        return step8ToHex;
    }

    public void clear(){
        step1WholeToBinary = new StringBuilder();
        step2DecimalPartToBinary = new StringBuilder();
        step3JoinWholeAndDecimal = "";
        step4Slipping = 0;
        step5CalcExponent = "";
        step6ExpToBinary = new StringBuilder();
        step7IEEENotation = "";
        step8ToHex = "";
    }

    private Procedure() {}
}
