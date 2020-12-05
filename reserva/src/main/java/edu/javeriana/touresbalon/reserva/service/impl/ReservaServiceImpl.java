package edu.javeriana.touresbalon.reserva.service.impl;

import edu.javeriana.touresbalon.reserva.client.BRMAPIClient;
import edu.javeriana.touresbalon.reserva.client.PagoAPIClient;
import edu.javeriana.touresbalon.reserva.dto.*;
import edu.javeriana.touresbalon.reserva.entities.Payment;
import edu.javeriana.touresbalon.reserva.entities.Producto;
import edu.javeriana.touresbalon.reserva.entities.Reserva;
import edu.javeriana.touresbalon.reserva.entities.Usuario;
import edu.javeriana.touresbalon.reserva.exceptions.NotFoundException;
import edu.javeriana.touresbalon.reserva.exceptions.RestClientException;
import edu.javeriana.touresbalon.reserva.kafka.KafkaProducer;
import edu.javeriana.touresbalon.reserva.repository.PaymentRepository;
import edu.javeriana.touresbalon.reserva.repository.ProductoRepository;
import edu.javeriana.touresbalon.reserva.repository.ReservaRepository;
import edu.javeriana.touresbalon.reserva.service.ReservaService;
import edu.javeriana.touresbalon.reserva.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PagoAPIClient pagoAPIClient;
    @Autowired
    private BRMAPIClient brmapiClient;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public ReservaResponse crearReserva(ReservaRequest reservaRequest) {

        //Se realiza el pago de la reserva
        boolean approval = brmapiClient.checkApproval(reservaRequest.getTotal(), reservaRequest.getUsuario().getCategoria());
        if (approval) {
            PagoRequest pagoRequest = new PagoRequest(reservaRequest.getPayment(), reservaRequest.getUsuario(),
                    reservaRequest.getReferencia(), reservaRequest.getTotal());
            PagoResponse pagoResponse = realizarPago(pagoRequest);
            //Se realiza la orden por cada uno de los productos de la lista
            for (ProductoDTO producto : reservaRequest.getProductList()) {
                kafkaProducer.sendProveedoresMessage(JsonConverter.toJSON(ProductoReservationOutputDTO.builder().
                        id(java.util.UUID.randomUUID().toString()).
                        productId(producto.getId()).providerId(producto.getProviderId()).
                        quantity(producto.getNumber()).type("COMMAND_CREATE_RESERVATION").build()));
            }

            guardarReserva(reservaRequest, pagoResponse, "IN_PROCESS");
            kafkaProducer.sendNotificacionesMessage(JsonConverter.toJSON(NotificationObject.builder().
                    email(reservaRequest.getUsuario().getEmail()).
                    nombreUsuario(reservaRequest.getUsuario().getFirstName() + reservaRequest.getUsuario().getLastName()).
                    valor(Integer.valueOf(pagoResponse.getValor())).
                    referencia(Integer.valueOf(pagoResponse.getReferencia())).
                    mensaje("Le informamos que su reserva esta en proceso").build()));
            return ReservaResponse.builder().pagoResponse(pagoResponse).usuario(reservaRequest.getUsuario()).
                    productList(reservaRequest.getProductList()).reservaStatus("IN_PROCESS").build();
        }else {
            guardarReserva(reservaRequest, null, "IN_APPROVAL");
            kafkaProducer.sendNotificacionesMessage(JsonConverter.toJSON(NotificationObject.builder().
                    email(reservaRequest.getUsuario().getEmail()).
                    nombreUsuario(reservaRequest.getUsuario().getFirstName() + reservaRequest.getUsuario().getLastName()).
                    valor(Integer.valueOf(reservaRequest.getTotal())).
                    referencia(Integer.valueOf("123456")).
                    mensaje("Le informamos que su reserva necesita aprobacion manual. Cuando esta sea realizada le notificaremos").build()));
            return ReservaResponse.builder().usuario(reservaRequest.getUsuario()).
                    productList(reservaRequest.getProductList()).reservaStatus("IN_APPROVAL").build();
        }
    }

    public PagoResponse realizarPago(PagoRequest pagoRequest) {

        try {
            PagoResponse pagoResponse = pagoAPIClient.createPayment(pagoRequest);
            return pagoResponse;
        } catch (Exception e) {
            throw new RestClientException("Servicio de pago no disponible. Intente de nuevo luego", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public PagoResponse compensarPago(PagoRequest pagoRequest) {

        try {
            PagoResponse pagoResponse = pagoAPIClient.compensatePayment(pagoRequest);
            return pagoResponse;
        } catch (Exception e) {
            throw new RestClientException("Servicio de pago no disponible. Intente de nuevo luego", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public void guardarReserva(ReservaRequest reservaRequest, PagoResponse pagoResponse, String status) {

        Payment pago = Payment.builder().idConvenio(reservaRequest.getPayment().getIdConvenio()).cardDate(reservaRequest.getPayment().getCardDate()).
                cardOwnerId(reservaRequest.getPayment().getCardOwnerId()).cardNumber(reservaRequest.getPayment().getCardNumber()).
                cardOwnerName(reservaRequest.getPayment().getCardOwnerName()).cardType(reservaRequest.getPayment().getCardType()).
                cvv(reservaRequest.getPayment().getCvv()).due(reservaRequest.getPayment().getDue()).build();
        Usuario usuario = Usuario.builder().id(reservaRequest.getUsuario().getId()).email(reservaRequest.getUsuario().getEmail()).
                firstName(reservaRequest.getUsuario().getFirstName()).lastName(reservaRequest.getUsuario().getLastName()).build();
        Reserva reserva = Reserva.builder().estado(status).fechaRegistro(new Timestamp(System.currentTimeMillis())).
                usuario(usuario).idPago(pagoResponse!=null?Long.parseLong(pagoResponse.getReferencia()):0l).
                valor(pagoResponse!=null?Long.parseLong(pagoResponse.getValor()):0l).build();
        usuario.setPayment(pago);
        reserva.setUsuario(usuario);
        reservaRepository.save(reserva);

        List<Producto> productoList = new ArrayList<>();
        for (ProductoDTO productoDTO : reservaRequest.getProductList()) {
            Producto producto = Producto.builder().idProducto(productoDTO.getId()).estado(status).
                    idProveedor(productoDTO.getProviderId()).reserva(reserva).build();
            productoList.add(producto);
        }
        productoRepository.saveAll(productoList);
    }

    @Override
    public void eliminarReserva(Reserva reserva) {

        PagoResponse pagoResponse = pagoAPIClient.consultarPago(reserva.getIdPago());
        PagoRequest pagoRequest = PagoRequest.builder().payment(PaymentDTO.builder().idConvenio(1).cardNumber(5412751234123456L).
                cardOwnerId(1010234755).cardDate("08-2020").cardType("MasterCard").cvv(242).due(24).
                cardOwnerName(reserva.getUsuario().getFirstName() + " " + reserva.getUsuario().getLastName()).build()).user(
                UsuarioDTO.builder().id(reserva.getUsuario().getId()).
                        email(reserva.getUsuario().getEmail()).firstName(reserva.getUsuario().getFirstName()).lastName(reserva.getUsuario().getLastName()).build()).
                referencia((int)reserva.getIdPago()).valor((int)reserva.getValor()).build();
        this.compensarPago(pagoRequest);
        for (Producto producto : reserva.getProductos()) {
            producto.setEstado("REJECTED");
        }
        reserva.setEstado("REJECTED");
        reservaRepository.save(reserva);
        kafkaProducer.sendNotificacionesMessage(
                construirMensajeConfirmacion(reserva, "Le informamos que su reserva ha sido rechazada y el pago ha sido compensado"));
    }

    @Override
    public Optional<Reserva> consultarReserva(int id) {

        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isEmpty())
            throw new NotFoundException("reserva no encontrada");
        return reserva;

    }

    @Override
    public ReservaResponse aprobarReserva(int id) {

        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isEmpty())
            throw new NotFoundException("reserva no encontrada");
        else{

            UsuarioDTO usuarioDTO = UsuarioDTO.builder().firstName(reserva.get().getUsuario().getFirstName()).
                    lastName(reserva.get().getUsuario().getLastName()).email(reserva.get().getUsuario().getEmail()).
                    id(reserva.get().getUsuario().getId()).build();

            PaymentDTO paymentDTO = PaymentDTO.builder().idConvenio(reserva.get().getUsuario().getPayment().getIdConvenio()).
                    cardDate(reserva.get().getUsuario().getPayment().getCardDate()).cardNumber(reserva.get().getUsuario().getPayment().getCardNumber()).
                    cardOwnerId(reserva.get().getUsuario().getPayment().getCardOwnerId()).cardOwnerName(reserva.get().getUsuario().getPayment().getCardOwnerName()).
                    cardType(reserva.get().getUsuario().getPayment().getCardType()).cvv(reserva.get().getUsuario().getPayment().getCvv()).
                    due(reserva.get().getUsuario().getPayment().getDue()).build();

            PagoRequest pagoRequest = new PagoRequest(paymentDTO, usuarioDTO,
                    reserva.get().getIdReserva(), (int) reserva.get().getValor());
            PagoResponse pagoResponse = realizarPago(pagoRequest);
            //Se realiza la orden por cada uno de los productos de la lista
            for (Producto producto : reserva.get().getProductos()) {
                kafkaProducer.sendProveedoresMessage(JsonConverter.toJSON(ProductoReservationOutputDTO.builder().
                        id(java.util.UUID.randomUUID().toString()).
                        productId(producto.getId()).providerId(producto.getIdProveedor()).
                        quantity(1).type("COMMAND_CREATE_RESERVATION").build()));
            }

            for (Producto producto : reserva.get().getProductos()) {
                producto.setEstado("IN_PROCESS");
            }
            reserva.get().setEstado("IN_PROCESS");
            reservaRepository.save(reserva.get());

            kafkaProducer.sendNotificacionesMessage(JsonConverter.toJSON(NotificationObject.builder().
                    email(reserva.get().getUsuario().getEmail()).
                    nombreUsuario(reserva.get().getUsuario().getFirstName() + reserva.get().getUsuario().getLastName()).
                    valor(Integer.valueOf(pagoResponse.getValor())).
                    referencia(Integer.valueOf(pagoResponse.getReferencia())).
                    mensaje("Le informamos que su reserva esta en proceso").build()));
            return ReservaResponse.builder().reservaStatus("IN_PROCESS").build();
        }
    }


    @Override
    public Iterable<Reserva> consultarListaReserva() {
        Iterable<Reserva> listaReserva = reservaRepository.findAll();
        if (!listaReserva.iterator().hasNext())
            throw new NotFoundException("No hay reservas en la lista");
        return listaReserva;
    }

    public String construirMensajeConfirmacion(Reserva reserva, String mensaje) {
        return JsonConverter.toJSON(NotificationObject.builder().
                email(reserva.getUsuario().getEmail()).
                nombreUsuario(reserva.getUsuario().getFirstName() + reserva.getUsuario().getLastName()).
                valor((int) reserva.getValor()).
                referencia(Integer.valueOf(reserva.getIdReserva())).
                mensaje(mensaje).build());
    }

}