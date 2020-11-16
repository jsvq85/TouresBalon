package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.TipoTransporte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoTransporteRepository extends CrudRepository<TipoTransporte, Integer> {

}