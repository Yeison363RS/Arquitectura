package util;

import models.IEEEConverter;
import models.Procedure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NotationConverter {

    public static String binaryStringToHexadecimal(String binary){
        if (binary.replace("0","").replace("1","").length() != 0){
            throw new IllegalArgumentException("String must contains only 0 and 1 values");
        }
        //normaliza la longitud del string a un multiplo de 4, ej: 10100 -> 00010100
        while (binary.length() % 4 != 0){
            binary = "0".concat(binary);
        }
        String result = "";
        for (int i = binary.length(); i > 3; i-=4) {
            String sub = binary.substring(i-4,i);
            int decimal = Integer.parseInt(sub,2);
            String hexValue = Integer.toString(decimal,16);
            Procedure.INSTANCE.addStepToHex(sub,hexValue);
            result = hexValue.concat(result);
        }
        return result.toUpperCase();
    }

    public static String listToString(List<Integer> integers) {
        return integers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    /**
     * Calcula la representación de un número entero a binario
     * @param numberUser entero a representar en binario
     * @return un array con los bits del número
     * @throws IllegalArgumentException si numberUser es negativo
     */
    public static ArrayList<Integer> getWholePartBits(int numberUser, StringBuilder builder) {
        //if (numberUser < 0) throw new IllegalArgumentException("Negative Value");
        numberUser=Math.abs(numberUser);
        ArrayList<Integer> binInt = new ArrayList<>();
        if (numberUser == 0) {
            Procedure.INSTANCE.addStepDecimalToBinary(builder," ","0");
            Procedure.INSTANCE.setResultDecimalToBinary(builder,"0");
            binInt.add(numberUser);
            return binInt;
        }
        while (numberUser > 0) {
            binInt.add(0,numberUser % 2);
            Procedure.INSTANCE.addStepDecimalToBinary(builder,""+numberUser%2,""+numberUser+"/2");
            numberUser = numberUser / 2;
        }
        Procedure.INSTANCE.setResultDecimalToBinary(builder,listToString(binInt));
        return binInt;
    }
}
