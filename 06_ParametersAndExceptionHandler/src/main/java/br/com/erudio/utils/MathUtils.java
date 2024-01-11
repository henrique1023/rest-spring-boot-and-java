package br.com.erudio.utils;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

public class MathUtils implements Serializable {

    public static Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;

        String number = strNumber.replaceAll(",",".");

        if(isNumeric(number)) return Double.parseDouble(number);

        return 0D;
    }

    public static boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;

        String number = strNumber.replaceAll(",",".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static void validationNumeric(String strNumber1, String strNumber2) throws Exception{
        if(!isNumeric(strNumber1) || !isNumeric(strNumber2)){
            throw  new UnsupportedOperationException("Please set a numeric value!");
        }
    }

    public static void validationNumeric(String strNumber1) throws Exception{
        if(!isNumeric(strNumber1)){
            throw  new UnsupportedOperationException("Please set a numeric value!");
        }
    }
}
