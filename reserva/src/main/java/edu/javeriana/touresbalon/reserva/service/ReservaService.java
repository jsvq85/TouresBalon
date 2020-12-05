package edu.javeriana.touresbalon.reserva.service;

import edu.javeriana.touresbalon.reserva.dto.ReservaRequest;
import edu.javeriana.touresbalon.reserva.dto.ReservaResponse;
import edu.javeriana.touresbalon.reserva.entities.Reserva;

import java.util.Optional;

public interface ReservaService {

    public ReservaResponse crearReserva(ReservaRequest reservaRequest);
    public void eliminarReserva(Reserva reserva);
    public Optional<Reserva> consultarReserva(int id);
    public ReservaResponse aprobarReserva(int id);
    public Iterable<Reserva> consultarListaReserva();

}
