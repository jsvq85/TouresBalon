package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.Localidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocalidadRepository extends CrudRepository<Localidad, Integer> {

}