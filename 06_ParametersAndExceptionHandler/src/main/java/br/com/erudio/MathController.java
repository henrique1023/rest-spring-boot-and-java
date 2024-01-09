package br.com.erudio;

import br.com.erudio.operation.MathOperations;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

import static br.com.erudio.utils.MathUtils.*;

@RestController
public class MathController {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        validationNumeric(numberOne,numberTwo);
        return MathOperations.Sum(numberOne,numberTwo);
    }
    @RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplicacao(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        validationNumeric(numberOne,numberTwo);
        return MathOperations.multiplicacao(numberOne,numberTwo);
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double divisao(@PathVariable(value = "numberOne") String numberOne,
                                @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        validationNumeric(numberOne,numberTwo);
        return MathOperations.divisao(numberOne,numberTwo);
    }

    @RequestMapping(value = "/media/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double media(@PathVariable(value = "numberOne") String numberOne,
                                @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        validationNumeric(numberOne,numberTwo);
        return MathOperations.media(numberOne,numberTwo);
    }

    @RequestMapping(value = "/raiz/{numberOne}", method = RequestMethod.GET)
    public Double raiz(@PathVariable(value = "numberOne") String numberOne) throws Exception {

        validationNumeric(numberOne);
        return MathOperations.raiz(numberOne);
    }


}
