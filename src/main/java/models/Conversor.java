package models;

import java.util.ArrayList;

public class Conversor {

    private ArrayList<Integer> mantisa=new ArrayList<Integer>();
    private ArrayList<Integer> exponent=new ArrayList<Integer>();
    private int signo=0;
    private double decimalValue = 0;
    
    public void convertSimpleFormat(double numberUser,int presicion){
        int partInt=(int)numberUser;
        double partDecimal=numberUser-partInt;
        ArrayList<Integer> binInteger=convertPartInt(partInt);     
        ArrayList<Integer> binDecimal=convertPartDecimal(partDecimal, 23-(binInteger.size()-1));
        signo = (numberUser>0)?0:1;
        if(partInt==0){
            int negativeShifts=calculateNegativeShifts(binDecimal);
            if(negativeShifts>0){
                binDecimal.addAll(convertPartDecimal(decimalValue, negativeShifts));
            }
            this.exponent = calculateExponent(presicion==1?8:11,-negativeShifts);
        }else{
            this.exponent=calculateExponent(presicion==1?8:11,binInteger.size()-1);
        }
        mantisa=conformateMantisa(binInteger,binDecimal);
    }

    public void printIEEEFormat(){
        System.out.println("signo: "+signo);
        System.out.println("exponent: "+exponent.toString());
        System.out.println("mantisa: "+mantisa.toString());
    }
    
    public ArrayList<Integer> calculateExponent(int precisionExponent,int shifts){
        int exponelDecimal=(int)Math.pow(2.0,precisionExponent-1)-1+shifts;
        ArrayList<Integer> exponent = convertPartInt(exponelDecimal);
        //System.out.println(exponent.size()+"  "+precisionExponent);
        while(exponent.size() != precisionExponent){
            exponent.add(0);
            //System.out.println("sdds");
        }
        return exponent;
    }

    public int calculateNegativeShifts(ArrayList<Integer> binDecimal){
        int shift=1;
        for (Integer integer : binDecimal) {
            if(integer!=0){
                return shift;
            }
            shift++;
        }
        return shift;
    }

    public ArrayList<Integer> conformateMantisa(ArrayList<Integer> binInteger,ArrayList<Integer> binDecimal){
        binInteger.addAll(binDecimal);
        while (binInteger.get(0)!=0) {
            binInteger.remove(0);
        }
        return binInteger;
    }

    public ArrayList<Integer> convertPartInt(int numberUser){
        ArrayList<Integer> binInt=new ArrayList<Integer>();
        while (numberUser>1) {
            binInt.add(numberUser%2);
            numberUser=numberUser/2;
        }
        binInt.add(numberUser);
        return binInt;
    }

    public ArrayList<Integer> convertPartDecimal(double decimal,int totalPresicion){
        ArrayList<Integer> binDecimal=new ArrayList<Integer>();
        while (totalPresicion>=0) {
            totalPresicion--;
            decimal*=2;
            if(decimal>0){
                binDecimal.add(1);
                decimal=decimal-1;
            }else{
                binDecimal.add(1);
            }
        }
        decimalValue=decimal;
        return binDecimal;
    }
}
