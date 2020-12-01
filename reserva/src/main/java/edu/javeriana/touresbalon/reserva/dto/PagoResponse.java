package edu.javeriana.touresbalon.reserva.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagoResponse {
  private String referencia;
  private String mensaje;
  private String valor;
  private String nroTransaccion;

}
