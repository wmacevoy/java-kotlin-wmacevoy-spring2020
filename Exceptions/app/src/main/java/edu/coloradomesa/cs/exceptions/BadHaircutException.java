package edu.coloradomesa.cs.exceptions;

public class BadHaircutException extends Exception {
    public BadHaircutException() { super(); }
    public BadHaircutException(String message) {
        super(message);
    }
    public BadHaircutException(String message, Throwable cause) {
        super(message,cause);
    }
    public BadHaircutException(Throwable cause) {
        super(cause);
    }
}
