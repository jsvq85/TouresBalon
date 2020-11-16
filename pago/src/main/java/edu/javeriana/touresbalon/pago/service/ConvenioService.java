package edu.javeriana.touresbalon.pago.service;

import edu.javeriana.touresbalon.model.ConvenioObject;
import edu.javeriana.touresbalon.model.ConvenioResponse;

import java.util.List;

public interface ConvenioService {

    ConvenioResponse getConvenioById(Integer partnerId);

    List<ConvenioResponse> getConvenioByName(String partnerName);

    ConvenioObject getInfoConvenio(Integer partnerId);
}
