package edu.javeriana.touresbalon.producto.service.impl;

import edu.javeriana.touresbalon.producto.dto.DetailEspectaculoDTO;
import edu.javeriana.touresbalon.producto.dto.EspectaculoDTO;
import edu.javeriana.touresbalon.producto.dto.LocacionDTO;
import edu.javeriana.touresbalon.producto.entities.Espectaculo;
import edu.javeriana.touresbalon.producto.entities.LocalidadEspectaculo;
import edu.javeriana.touresbalon.producto.exceptions.NotFoundException;
import edu.javeriana.touresbalon.producto.repository.EspectaculoRepository;
import edu.javeriana.touresbalon.producto.repository.LocalidadEspectaculoRepository;
import edu.javeriana.touresbalon.producto.service.EspectaculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class EspectaculoServiceImpl implements EspectaculoService {

    @Autowired
    private EspectaculoRepository espectaculoRepository;

    @Autowired
    private LocalidadEspectaculoRepository localidadEspectaculoRepository;

    @Override
    public Espectaculo crearEspectaculo(Espectaculo espectaculo) {

        espectaculoRepository.save(espectaculo);

        return espectaculo;
    }

    @Override
    public void eliminarEspectaculo(Espectaculo espectaculo) {

        espectaculoRepository.delete(espectaculo);

    }

    @Override
    public DetailEspectaculoDTO consultarEspectaculo(int id) {
        Optional<Espectaculo> espectaculo = espectaculoRepository.findById(id);
        if (espectaculo.isEmpty())
            throw new NotFoundException("Espectaculo no encontrado");

        List<LocalidadEspectaculo> localidades = getLocalidadesByEvent(espectaculo.get());

        return DetailEspectaculoDTO.builder()
                .id(espectaculo.get().getIdProducto())
                .image(espectaculo.get().getImagen())
                .imageLocalities(espectaculo.get().getImagenLugar())
                .name(espectaculo.get().getNombre())
                .description(espectaculo.get().getDescripcion())
                .place(espectaculo.get().getLugar())
                .city(espectaculo.get().getCiudad())
                .date(espectaculo.get().getFecha())
                .category(espectaculo.get().getTipoEspectaculo().getNombre())
                .capacity(espectaculo.get().getAforo())
                .localities(
                        localidades.stream().map(
                                e -> LocacionDTO.builder()
                                        .id(e.getLocalidad().getIdLocalidad())
                                        .name(e.getNombreLocalidad())
                                        .price(e.getPrecio())
                                        .build())
                                .collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<EspectaculoDTO> consultarListaEspectaculos() {
        Iterable<Espectaculo> listaEspectaculos = espectaculoRepository.findAll();
        if (!listaEspectaculos.iterator().hasNext())
            throw new NotFoundException("No hay espectaculos en la lista");
        else
            return StreamSupport.stream(listaEspectaculos.spliterator(), false)
                    .map( e -> EspectaculoDTO.builder()
                            .id(e.getIdProducto())
                            .image(e.getImagen())
                            .name(e.getNombre())
                            .description(e.getDescripcion())
                            .category(e.getTipoEspectaculo().getNombre())
                            .price(getMaxAndMinPrice(getLocalidadesByEvent(e)))
                            .build()).collect(Collectors.toList());
    }

    private List<Double> getMaxAndMinPrice(List<LocalidadEspectaculo> le){
        Double max = le.stream().map(e -> e.getPrecio()).max(Comparator.naturalOrder()).get();
        Double min = le.stream().map(e -> e.getPrecio()).min(Comparator.naturalOrder()).get();
        return List.of(min,max);
    }

    private List<LocalidadEspectaculo> getLocalidadesByEvent(Espectaculo e){
        return localidadEspectaculoRepository.findLocalidadByEspectaculo(e);
    }

}
