package org.sursmobil.touchy.core;

/**
 * Created by CJ on 11/08/2015.
 */
public class TouchyException extends RuntimeException {
    public TouchyException(String message) {
        super(message);
    }

    public TouchyException(String message, Throwable cause) {
        super(message, cause);
    }
}
