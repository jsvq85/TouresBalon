package edu.javeriana.touresbalon.pago.service;

import edu.javeriana.touresbalon.pago.model.ConvenioObject;
import edu.javeriana.touresbalon.pago.model.ConvenioResponse;

import java.util.List;

public interface ConvenioService {

    ConvenioResponse getConvenioById(Integer partnerId);

    List<ConvenioResponse> getConvenioByName(String partnerName);

    ConvenioObject getInfoConvenio(Integer partnerId);
}
