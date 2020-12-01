package edu.javeriana.touresbalon.reserva.exceptions;

public class ApiRetryException extends RuntimeException {
    public ApiRetryException(String message) {
        super(message);
    }
}
