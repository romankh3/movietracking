package ua.romankh3.movietracking.exception;

/**
 * Common exception for the validation.
 */
public class ValidationException extends Exception {

    public ValidationException(String msg) {
        super(msg);
    }
}