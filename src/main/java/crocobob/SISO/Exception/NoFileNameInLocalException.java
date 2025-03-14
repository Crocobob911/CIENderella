package crocobob.SISO.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoFileNameInLocalException extends RuntimeException {
    public NoFileNameInLocalException(String message) {
        super("ERROR 404 : " + message);
    }
}
