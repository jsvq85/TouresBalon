package edu.javeriana.touresbalon.reserva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequest {

  private PaymentDTO payment;
  private UsuarioDTO user;
  private Integer referencia;
  private Integer valor;

  public PagoRequest(PaymentDTO payment, UsuarioDTO usuario, Integer total) {

    this.payment = payment;
    this.user = usuario;
    this.valor = total;
    this.referencia = referencia;

  }
}
