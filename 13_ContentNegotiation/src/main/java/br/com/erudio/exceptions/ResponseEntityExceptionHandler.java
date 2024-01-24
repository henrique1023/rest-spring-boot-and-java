package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResponseEntityExceptionHandler extends RuntimeException{
    public ResponseEntityExceptionHandler(String message) {
        super(message);
    }
}
