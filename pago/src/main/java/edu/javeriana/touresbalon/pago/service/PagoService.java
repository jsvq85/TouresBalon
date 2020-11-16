package edu.javeriana.touresbalon.pago.service;

import edu.javeriana.touresbalon.pago.model.PagoRequest;
import edu.javeriana.touresbalon.pago.model.PagoResponse;

public interface PagoService {

    PagoResponse consultarFactura(Integer idConvenio, Integer referencia);

    PagoResponse pagarFactura(PagoRequest data);

    PagoResponse compensarFactura(PagoRequest data);
}
