package edu.javeriana.touresbalon.pago.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConvenioNotFoundException extends RuntimeException {

  public ConvenioNotFoundException(String message) {
    super(message);
  }
}
