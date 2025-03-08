package crocobob.CIENderella.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidContentTimeDomainException extends RuntimeException {
    public InvalidContentTimeDomainException(String message) {
        super("ERROR 400 : " + message);
    }
}
