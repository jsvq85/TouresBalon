package edu.javeriana.touresbalon.proveedor.service.impl;

import edu.javeriana.touresbalon.proveedor.exceptions.ConvenioNotFoundException;
import edu.javeriana.touresbalon.proveedor.model.ConvenioObject;
import edu.javeriana.touresbalon.proveedor.model.ConvenioResponse;
import edu.javeriana.touresbalon.proveedor.repository.ConvenioRepository;
import edu.javeriana.touresbalon.proveedor.repository.IConfiguracionRepository;
import edu.javeriana.touresbalon.proveedor.repository.IEncabezadoRepository;
import edu.javeriana.touresbalon.proveedor.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvenioServiceImpl implements ConvenioService {

    private ConvenioRepository cr;
    private IConfiguracionRepository confR;
    private IEncabezadoRepository encR;

    @Autowired
    public ConvenioServiceImpl(ConvenioRepository cr, IConfiguracionRepository confR, IEncabezadoRepository encR) {
        this.cr = cr;
        this.confR = confR;
        this.encR = encR;
    }

    @Override
    public ConvenioResponse getConvenioById(Integer partnerId) {
        return new ConvenioResponse(cr.getConvenioById(partnerId).
                orElseThrow(() -> new ConvenioNotFoundException
                        ("No se encuentran convenios registrados con el id." + partnerId)));
    }

    @Override
    public List<ConvenioResponse> getConvenioByName(String partnerName) {
        List<ConvenioResponse> rta = cr
                .getConvenioByName(partnerName)
                .stream()
                .map(ConvenioResponse::new)
                .collect(Collectors.toList());
        if (rta.size() != 0) {
            return rta;
        } else {
            throw new ConvenioNotFoundException("No se encuentran convenios que el nombre coincida con " + partnerName);
        }
    }

    @Override
    public ConvenioObject getInfoConvenio(Integer partnerId) {
        ConvenioObject rta = cr.getConvenioById2(partnerId).
                orElseThrow(() -> new ConvenioNotFoundException
                        ("No se encuentran convenios registrados con el id." + partnerId));
        rta.setConfiguracion(confR.getConfigByConvenio(partnerId));
        rta.getConfiguracion().forEach(x -> x.setServiceHeaders(encR.getHeaderByConfiguration(x.getConfigurationId())));
        return rta;
    }

}
