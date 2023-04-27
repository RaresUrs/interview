package com.tnt.digital.exception;

public class OrderNumberException extends RuntimeException {

    public OrderNumberException(String message) {
        super(message);
    }

    public OrderNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
