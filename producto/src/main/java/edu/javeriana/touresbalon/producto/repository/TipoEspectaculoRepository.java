package edu.javeriana.touresbalon.producto.repository;

import edu.javeriana.touresbalon.producto.entities.TipoEspectaculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoEspectaculoRepository extends CrudRepository<TipoEspectaculo, Integer> {

}