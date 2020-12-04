package edu.javeriana.touresbalon.pago.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoResponse {
  private String referencia;
  private String mensaje;
  private String valor;
  private String nroTransaccion;

}
