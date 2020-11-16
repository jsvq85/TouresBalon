package edu.javeriana.touresbalon.producto.repository;

import edu.javeriana.touresbalon.producto.entities.TipoAlojamiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoAlojamientoRepository extends CrudRepository<TipoAlojamiento, Integer> {

}