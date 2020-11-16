package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.TipoAlojamiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoAlojamientoRepository extends CrudRepository<TipoAlojamiento, Integer> {

}