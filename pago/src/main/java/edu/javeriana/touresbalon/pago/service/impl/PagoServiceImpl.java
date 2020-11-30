package edu.javeriana.touresbalon.pago.service.impl;

import edu.javeriana.touresbalon.pago.clients.AGenericClient;
import edu.javeriana.touresbalon.pago.clients.ClientFactory;
import edu.javeriana.touresbalon.pago.clients.ProveedorAPIClient;
import edu.javeriana.touresbalon.pago.clients.UsuarioAPIClient;
import edu.javeriana.touresbalon.pago.dto.UsuarioDTO;
import edu.javeriana.touresbalon.pago.entities.Usuario;
import edu.javeriana.touresbalon.pago.exceptions.PaymentBadRequestException;
import edu.javeriana.touresbalon.pago.exceptions.PaymentNotFoundException;
import edu.javeriana.touresbalon.pago.kafka.KafkaProducer;
import edu.javeriana.touresbalon.pago.model.*;
import edu.javeriana.touresbalon.pago.repository.UsuarioRepository;
import edu.javeriana.touresbalon.pago.service.ConvenioService;
import edu.javeriana.touresbalon.pago.service.PagoService;
import edu.javeriana.touresbalon.pago.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

  @Autowired
  private UsuarioAPIClient usuarioAPIClient;
  @Autowired
  private ProveedorAPIClient proveedorAPIClient;
  @Autowired
  private ConvenioService convenioService;
  @Autowired
  private KafkaProducer kafkaProducer;

  public PagoServiceImpl() {

  }

  private ServiceDescription getConfiguration(Integer idConvenio, String operacion) {
    // Se obtiene la información del convenio
    ConvenioObject co = convenioService.getInfoConvenio(idConvenio);
    // Se obtiene la configuración para el servicio de consulta.
    ServiceDescription sd = co.getConfiguracion().stream().filter(x -> x.getOperation().equals(operacion)).findFirst().orElse(null);
    if (sd == null) {
      throw new PaymentNotFoundException("El convenio de recaudo especificado no existe");
    }
    return sd;
  }

  private String fillPayload(String payload, Integer reference) {
    if (payload != null && reference != null) {
      return payload.replace("$1", reference.toString());
    }
    return payload;
  }

  private String fillPayload(String payload, Integer reference, Integer valor) {
    if (payload != null) {
      return fillPayload(payload, reference).replace("$2", valor == null ? "" : valor.toString());
    }
    return payload;
  }

  @Override
  public PagoResponse consultarFactura(Integer idConvenio, Integer referencia) {
    if (idConvenio == null || referencia == null) {
      throw new PaymentBadRequestException("Debe especificar el identificador del convenio" +
              " y el número de referencia de recaudo o factura");
    }
    PagoResponse rta;
    ServiceDescription sd = getConfiguration(idConvenio, "CONSULTAR");
    // Se establece el valor de la referencia a consultar
    sd.setRequestPayload(fillPayload(sd.getRequestPayload(), referencia));
    sd.setServiceUrl(fillPayload(sd.getServiceUrl(), referencia));
    AGenericClient client = ClientFactory.getClient(sd.getServiceType());
    return client.callService(sd);
  }

  @Override
  public PagoResponse pagarFactura(PagoRequest data) {
    if (data.getIdConvenio() == null || data.getReferencia() == null || data.getValor() == null) {
      throw new PaymentBadRequestException("Debe especificar el identificador del convenio " +
              "y el número de referencia de recaudo o factura");
    }
    PagoResponse rta;
    ServiceDescription sd = getConfiguration(data.getIdConvenio(), "PAGAR");
    // Se establece el valor de la referencia a consultar
    sd.setRequestPayload(fillPayload(sd.getRequestPayload(), data.getReferencia(), data.getValor()));
    sd.setServiceUrl(fillPayload(sd.getServiceUrl(), data.getReferencia(), data.getValor()));
    AGenericClient client = ClientFactory.getClient(sd.getServiceType());
    NotificationObject info = new NotificationObject();
    info.setServicio("PAGAR");
    info.setEmail(data.getUser().getEmail());
    info.setIdUsuario(data.getUser().getId());
    info.setNombreUsuario(data.getUser().getFirstName()+" "+data.getUser().getLastName());
    info.setIdConvenio(data.getIdConvenio());
    info.setReferencia(data.getReferencia());
    info.setValor(data.getValor());
    info.setNroTransaccion(1);
    rta = client.callService(sd);
    info.setMensaje(rta.getMensaje());
    //kafkaProducer.sendMessage(JsonConverter.toJSON(info));
    return rta;
  }

  @Override
  public PagoResponse compensarFactura(PagoRequest data) {
    if (data.getIdConvenio() == null || data.getReferencia() == null || data.getValor() == null) {
      throw new PaymentBadRequestException("Debe especificar el identificador del convenio y el número de referencia de recaudo o factura");
    }
    PagoResponse rta;
    ServiceDescription sd = getConfiguration(data.getIdConvenio(), "COMPENSAR");
    sd.setRequestPayload(fillPayload(sd.getRequestPayload(), data.getReferencia(), data.getValor()));
    sd.setServiceUrl(fillPayload(sd.getServiceUrl(), data.getReferencia(), data.getValor()));
    AGenericClient client = ClientFactory.getClient(sd.getServiceType());
    NotificationObject info = new NotificationObject();
    info.setServicio("COMPENSAR");
    info.setEmail(data.getUser().getEmail());
    info.setIdUsuario(data.getUser().getId());
    info.setNombreUsuario(data.getUser().getFirstName()+" "+data.getUser().getLastName());
    info.setIdConvenio(data.getIdConvenio());
    info.setReferencia(data.getReferencia());
    info.setValor(data.getValor());
    info.setNroTransaccion(1);
    rta = client.callService(sd);
    info.setMensaje(rta.getMensaje());
    //kafkaProducer.sendMessage(JsonConverter.toJSON(info));
    return rta;
  }


}
