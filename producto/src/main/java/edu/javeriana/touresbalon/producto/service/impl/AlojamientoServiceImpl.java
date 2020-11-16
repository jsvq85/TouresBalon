package edu.javeriana.touresbalon.producto.service.impl;

import edu.javeriana.touresbalon.producto.entities.Alojamiento;
import edu.javeriana.touresbalon.producto.exceptions.NotFoundException;
import edu.javeriana.touresbalon.producto.repository.AlojamientoRepository;
import edu.javeriana.touresbalon.producto.service.AlojamientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AlojamientoServiceImpl implements AlojamientoService {

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    @Override
    public Alojamiento crearAlojamiento(Alojamiento alojamiento) {

        alojamientoRepository.save(alojamiento);

        return alojamiento;
    }
    @Override
    public void eliminarAlojamiento(Alojamiento alojamiento) {

        alojamientoRepository.delete(alojamiento);

    }

    @Override
    public Optional<Alojamiento> consultarAlojamiento(int id) {

        Optional<Alojamiento> alojamiento = alojamientoRepository.findById(id);
        if(alojamiento.isEmpty())
            throw new NotFoundException("Alojamiento no encontrado");
        return alojamiento;

    }

    @Override
    public Iterable<Alojamiento> consultarListaAlojamientos() {
        Iterable<Alojamiento> listaAlojamientos = alojamientoRepository.findAll();
        if(!listaAlojamientos.iterator().hasNext())
            throw new NotFoundException("No hay alojamientos en la lista");
        return listaAlojamientos;
    }

}
