package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.TipoEspectaculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoEspectaculoRepository extends CrudRepository<TipoEspectaculo, Integer> {

}