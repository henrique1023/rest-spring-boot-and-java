package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectisNullException extends RuntimeException{
    public RequiredObjectisNullException(String message) {
        super(message);
    }

    public RequiredObjectisNullException() {
        super("It is not allowed to persist a null object!");
    }
}
