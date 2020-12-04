package edu.javeriana.touresbalon.reserva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoRequest {

  private PaymentDTO payment;
  private UsuarioDTO user;
  private Integer referencia;
  private Integer valor;

}
