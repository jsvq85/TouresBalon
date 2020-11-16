package edu.javeriana.touresbalon.proveedor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RemoteResponse {
  private String reference;
  private String value;
  private String message;
}
