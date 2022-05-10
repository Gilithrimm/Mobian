package com.safenar;

public class BadStructureException extends Exception {
    public BadStructureException() {
        super();
    }

    public BadStructureException(String message) {
        super(message);
    }

    public BadStructureException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadStructureException(Throwable cause) {
        super(cause);
    }
}
