package models;

import util.NotationConverter;

import java.util.ArrayList;

public class Procedure {

    public static final Procedure INSTANCE = new Procedure();

    //Corrimiento
    int step4Slipping;
    StringBuilder step1WholeToBinary = new StringBuilder();
    StringBuilder step2DecimalPartToBinary = new StringBuilder();
    String step3JoinWholeAndDecimal = "";
    StringBuilder step6ExpToBinary = new StringBuilder();

    public void addStepDecimalToBinary(StringBuilder builder, String remainder, String value){
        builder.append(String.format("\t%s|%s\n",remainder,value));
    }

    public void setResultDecimalToBinary(StringBuilder builder, String value){
        builder.append("\nResultado: ").append(value);
    }

    public void setStep3JoinWholeAndDecimal(ArrayList<Integer> wholePart, ArrayList<Integer> decimalPart){
        step3JoinWholeAndDecimal = NotationConverter.listToString(wholePart)+","+
                NotationConverter.listToString(decimalPart);
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

    public String getStep6ExpToBinary() {
        return step6ExpToBinary.toString();
    }

    public void clear(){
        step4Slipping = 0;
        step1WholeToBinary = new StringBuilder();
        step2DecimalPartToBinary = new StringBuilder();
        step3JoinWholeAndDecimal = "";
        step6ExpToBinary = new StringBuilder();
    }

    private Procedure() {}
}
