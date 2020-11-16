package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.Ubicacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UbicacionRepository extends CrudRepository<Ubicacion, Integer> {

}