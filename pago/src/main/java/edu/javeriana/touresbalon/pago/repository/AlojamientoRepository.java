package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.Alojamiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlojamientoRepository extends CrudRepository<Alojamiento, Integer> {

}