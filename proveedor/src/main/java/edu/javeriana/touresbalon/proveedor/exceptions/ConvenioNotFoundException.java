package edu.javeriana.touresbalon.proveedor.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConvenioNotFoundException extends RuntimeException {

  public ConvenioNotFoundException(String message) {
    super(message);
  }
}
