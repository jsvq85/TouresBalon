package edu.javeriana.touresbalon.pago.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequest {

  private PaymentObject payment;
  private UsuarioObject user;
  private Integer idConvenio;
  private Integer referencia;
  private Integer valor;
}
