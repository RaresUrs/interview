package com.tnt.digital.exception;

public class InvalidCountryCodeException extends RuntimeException {

    public InvalidCountryCodeException(String message) {
        super(message);
    }

    public InvalidCountryCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
