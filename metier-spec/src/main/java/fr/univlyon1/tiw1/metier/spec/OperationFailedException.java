package fr.univlyon1.tiw1.metier.spec;

/**
 * Thrown when an error occurs during some business operation.
 * <p>
 * Created by ecoquery on 27/06/2017.
 */
public class OperationFailedException extends Exception {
    /**
     * Creates an empty OperationFailedException
     */
    public OperationFailedException() {
        super();
    }

    /**
     * Creates an OperationFailedException.
     *
     * @param message the exception's message
     */
    public OperationFailedException(String message) {
        super(message);
    }

    /**
     * Creates an OperationFailedException.
     *
     * @param message the exception's message
     * @param cause   the exception's cause
     */
    public OperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates an OperationFailedException.
     *
     * @param cause the exception's cause
     */
    public OperationFailedException(Throwable cause) {
        super(cause);
    }

    protected OperationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
