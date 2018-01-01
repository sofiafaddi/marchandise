package fr.univlyon1.tiw1.tp3.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/30/17.
 *
 * Exception when an entity is not founded with a given identifier or reference
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends Exception {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }


    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
