package edu.javeriana.touresbalon.producto.repository;

import edu.javeriana.touresbalon.producto.entities.TipoTransporte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoTransporteRepository extends CrudRepository<TipoTransporte, Integer> {

}