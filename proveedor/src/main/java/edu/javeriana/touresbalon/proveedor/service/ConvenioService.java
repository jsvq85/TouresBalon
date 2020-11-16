package edu.javeriana.touresbalon.proveedor.service;

import edu.javeriana.touresbalon.proveedor.model.ConvenioObject;
import edu.javeriana.touresbalon.proveedor.model.ConvenioResponse;

import java.util.List;

public interface ConvenioService {

    ConvenioResponse getConvenioById(Integer partnerId);

    List<ConvenioResponse> getConvenioByName(String partnerName);

    ConvenioObject getInfoConvenio(Integer partnerId);
}
