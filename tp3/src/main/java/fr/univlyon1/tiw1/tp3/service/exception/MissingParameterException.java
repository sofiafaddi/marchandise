package fr.univlyon1.tiw1.tp3.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/30/17.
 *
 * Exception when a required parameter is not founded.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingParameterException extends RuntimeException {

    public MissingParameterException() {
        super();
    }

    public MissingParameterException(String message) {
        super(message);
    }

    public MissingParameterException(String message, Throwable cause) {
        super(message, cause);
    }


    public MissingParameterException(Throwable cause) {
        super(cause);
    }

    protected MissingParameterException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
