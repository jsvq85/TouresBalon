package edu.javeriana.touresbalon.reserva.service.impl;

import edu.javeriana.touresbalon.reserva.client.PagoAPIClient;
import edu.javeriana.touresbalon.reserva.dto.*;
import edu.javeriana.touresbalon.reserva.entities.Producto;
import edu.javeriana.touresbalon.reserva.entities.Reserva;
import edu.javeriana.touresbalon.reserva.entities.Usuario;
import edu.javeriana.touresbalon.reserva.exceptions.NotFoundException;
import edu.javeriana.touresbalon.reserva.exceptions.RestClientException;
import edu.javeriana.touresbalon.reserva.kafka.KafkaProducer;
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
    private PagoAPIClient pagoAPIClient;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public ReservaResponse crearReserva(ReservaRequest reservaRequest) {

        //Se realiza el pago de la reserva
        PagoRequest pagoRequest = new PagoRequest(reservaRequest.getPayment(),reservaRequest.getUsuario(),
                reservaRequest.getReferencia(),reservaRequest.getTotal());
        PagoResponse pagoResponse = realizarPago(pagoRequest);
        //Se realiza la orden por cada uno de los productos de la lista
        for (ProductoDTO producto:reservaRequest.getProductList()) {
            kafkaProducer.sendProveedoresMessage(JsonConverter.toJSON(ProductoReservationOutputDTO.builder().
                    id(java.util.UUID.randomUUID().toString()).
                    productId(producto.getId()).providerId(producto.getProviderId()).
                    quantity(producto.getNumber()).type("COMMAND_CREATE_RESERVATION").build()));
        }

        guardarReserva(reservaRequest, pagoResponse);
        kafkaProducer.sendNotificacionesMessage(JsonConverter.toJSON(NotificationObject.builder().
                email(reservaRequest.getUsuario().getEmail()).
                nombreUsuario(reservaRequest.getUsuario().getFirstName() + reservaRequest.getUsuario().getLastName()).
                valor(Integer.valueOf(pagoResponse.getValor())).
                referencia(Integer.valueOf(pagoResponse.getReferencia())).
                mensaje("Le informamos que su reserva esta en proceso").build()));
        return ReservaResponse.builder().pagoResponse(pagoResponse).usuario(reservaRequest.getUsuario()).
                productList(reservaRequest.getProductList()).reservaStatus("IN_PROCESS").build();
    }

    public PagoResponse realizarPago(PagoRequest pagoRequest){

        try {
            PagoResponse pagoResponse = pagoAPIClient.createPayment(pagoRequest);
            return pagoResponse;
        }catch(Exception e) {
            throw new RestClientException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public PagoResponse compensarPago(PagoRequest pagoRequest){

        try {
            PagoResponse pagoResponse = pagoAPIClient.compensatePayment(pagoRequest);
            return pagoResponse;
        }catch(Exception e) {
            throw new RestClientException(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public void guardarReserva(ReservaRequest reservaRequest, PagoResponse pagoResponse){

        Usuario usuario = Usuario.builder().id(reservaRequest.getUsuario().getId()).email(reservaRequest.getUsuario().getEmail()).
                firstName(reservaRequest.getUsuario().getFirstName()).lastName(reservaRequest.getUsuario().getLastName()).build();

        Reserva reserva = Reserva.builder().estado("IN_PROCESS").fechaRegistro(new Timestamp(System.currentTimeMillis())).
                usuario(usuario).idPago(Long.parseLong(pagoResponse.getReferencia())).valor(Long.parseLong(pagoResponse.getValor())).build();
        reservaRepository.save(reserva);

        List<Producto> productoList = new ArrayList<>();
        for (ProductoDTO productoDTO:reservaRequest.getProductList()) {
            Producto producto = Producto.builder().idProducto(productoDTO.getId()).estado("IN_PROCESS").
                    idProveedor(productoDTO.getProviderId()).reserva(reserva).build();
            productoList.add(producto);
        }
        productoRepository.saveAll(productoList);
    }

    @Override
    public void eliminarReserva(Reserva reserva) {

        reservaRepository.delete(reserva);
    }

    @Override
    public Optional<Reserva> consultarReserva(int id) {

        Optional<Reserva> reserva = reservaRepository.findById(id);
        if(reserva.isEmpty())
            throw new NotFoundException("reserva no encontrada");
        return reserva;

    }

    @Override
    public Iterable<Reserva> consultarListaReserva() {
        Iterable<Reserva> listaReserva = reservaRepository.findAll();
        if(!listaReserva.iterator().hasNext())
            throw new NotFoundException("No hay reservas en la lista");
        return listaReserva;
    }

}