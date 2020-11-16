package edu.javeriana.touresbalon.pago.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderService {
  private String headerName;
  private String headerValue;
}
