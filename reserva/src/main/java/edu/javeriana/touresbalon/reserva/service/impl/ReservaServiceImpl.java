package edu.javeriana.touresbalon.reserva.service.impl;

import edu.javeriana.touresbalon.reserva.client.PagoAPIClient;
import edu.javeriana.touresbalon.reserva.dto.*;
import edu.javeriana.touresbalon.reserva.entities.Reserva;
import edu.javeriana.touresbalon.reserva.exceptions.NotFoundException;
import edu.javeriana.touresbalon.reserva.kafka.KafkaProducer;
import edu.javeriana.touresbalon.reserva.repository.ReservaRepository;
import edu.javeriana.touresbalon.reserva.service.ReservaService;
import edu.javeriana.touresbalon.reserva.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private PagoAPIClient pagoAPIClient;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public ReservaResponse crearReserva(ReservaRequest reservaRequest) {

        //Se realiza el pago de la reserva
        PagoRequest pagoRequest = new PagoRequest(reservaRequest.getPayment(),reservaRequest.getUsuario(),
                reservaRequest.getReferencia(),reservaRequest.getTotal());
        PagoResponse pagoResponse = pagoAPIClient.createPayment(pagoRequest);
        //Se realiza la orden por cada uno de los productos de la lista
        for (ProductoDTO producto:reservaRequest.getProductList()) {
            kafkaProducer.sendMessage(JsonConverter.toJSON(ProductoReservationDTO.builder().
                    productId(producto.getId()).providerId(producto.getProviderId()).
                    quantity(producto.getNumber()).build()));
        }
        Reserva reserva = new Reserva();
        //reservaRepository.save(reserva);
        ReservaResponse reservaResponse = new ReservaResponse();
        reservaResponse.setPagoResponse(pagoResponse);
        reservaResponse.setUsuario(reservaRequest.getUsuario());
        reservaResponse.setReservaStatus("ACEPTADA");
        return reservaResponse;
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