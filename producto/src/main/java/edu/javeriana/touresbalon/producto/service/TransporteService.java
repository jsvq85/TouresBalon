package edu.javeriana.touresbalon.producto.service;

import edu.javeriana.touresbalon.producto.entities.Transporte;

import java.util.Optional;

public interface TransporteService {

    Transporte crearTransporte(Transporte transporte);

    void eliminarTransporte(Transporte transporte);

    Optional<Transporte> consultarTransporte(int id);

    Iterable<Transporte> consultarListaTransportes();
}
