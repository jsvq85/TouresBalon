package edu.javeriana.touresbalon.pago.exceptions;

public class PaymentBadRequestException extends RuntimeException {
  public PaymentBadRequestException(String message) {
    super(message);
  }
}
