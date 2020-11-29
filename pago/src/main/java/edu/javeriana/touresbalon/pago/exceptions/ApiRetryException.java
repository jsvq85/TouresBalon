package edu.javeriana.touresbalon.pago.exceptions;

public class ApiRetryException extends RuntimeException {
    public ApiRetryException(String message) {
        super(message);
    }
}
