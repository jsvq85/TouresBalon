package edu.javeriana.touresbalon.pago.model;

import edu.javeriana.touresbalon.pago.dto.ConvenioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvenioResponse {
  private Integer idConvenio;
  private String nombre;

  public ConvenioResponse(ConvenioDTO data) {
    idConvenio = data.getPartnerId();
    nombre = data.getPartnerName();
  }
}
