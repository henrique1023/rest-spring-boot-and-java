package br.com.erudio.operation;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

import static br.com.erudio.utils.MathUtils.convertToDouble;

@RestController
public class MathOperations implements Serializable {
    public static Double Sum(String numberOne, String numberTwo) {
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }
    public static Double multiplicacao(String numberOne, String numberTwo) {
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }
    public static Double divisao(String numberOne, String numberTwo) {
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }
    public static Double media(String numberOne, String numberTwo) {
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }
    public static Double raiz(String numberOne) {
        return Math.sqrt(convertToDouble(numberOne));
    }

}
