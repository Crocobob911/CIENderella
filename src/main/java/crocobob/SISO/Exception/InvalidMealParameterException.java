package crocobob.SISO.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidMealParameterException extends RuntimeException {
    public InvalidMealParameterException(String message) {
        super("ERROR 404 : " + message);
    }
}
