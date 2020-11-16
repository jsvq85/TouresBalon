package edu.javeriana.touresbalon.producto.service;

import edu.javeriana.touresbalon.producto.dto.DetailEspectaculoDTO;
import edu.javeriana.touresbalon.producto.dto.EspectaculoDTO;
import edu.javeriana.touresbalon.producto.entities.Espectaculo;

import java.util.List;

public interface EspectaculoService {

    Espectaculo crearEspectaculo(Espectaculo espectaculo);

    void eliminarEspectaculo(Espectaculo espectaculo);

    DetailEspectaculoDTO consultarEspectaculo(int id);

    List<EspectaculoDTO> consultarListaEspectaculos();
}
