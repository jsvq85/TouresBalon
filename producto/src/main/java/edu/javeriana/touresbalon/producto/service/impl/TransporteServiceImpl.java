package edu.javeriana.touresbalon.producto.service.impl;

import edu.javeriana.touresbalon.producto.entities.TipoTransporte;
import edu.javeriana.touresbalon.producto.entities.Transporte;
import edu.javeriana.touresbalon.producto.exceptions.NotFoundException;
import edu.javeriana.touresbalon.producto.repository.TipoTransporteRepository;
import edu.javeriana.touresbalon.producto.repository.TransporteRepository;
import edu.javeriana.touresbalon.producto.service.TransporteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TransporteServiceImpl implements TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;
    @Autowired
    private TipoTransporteRepository tipoTransporteRepository;

    @Override
    public Transporte crearTransporte(Transporte transporte) {

        Optional<TipoTransporte> tipoTransporte = tipoTransporteRepository.findById(transporte.getTipoTransporte().getIdTipoTransporte());
        transporte.setTipoTransporte(tipoTransporte.orElse(null));
        transporte = transporteRepository.save(transporte);

        return transporte;
    }
    @Override
    public void eliminarTransporte(Transporte transporte) {
        transporteRepository.delete(transporte);

    }

    @Override
    public Optional<Transporte> consultarTransporte(int id) {

        Optional<Transporte> producto = transporteRepository.findById(id);
        if(producto.isEmpty())
            throw new NotFoundException("Transporte no encontrado");
        return producto;

    }

    @Override
    public Iterable<Transporte> consultarListaTransportes() {
        Iterable<Transporte> listaTransportes = transporteRepository.findAll();
        if(!listaTransportes.iterator().hasNext())
            throw new NotFoundException("No hay transportes en la lista");
        return listaTransportes;
    }

}
