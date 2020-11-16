package edu.javeriana.touresbalon.producto.repository;

import edu.javeriana.touresbalon.producto.entities.Localidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocalidadRepository extends CrudRepository<Localidad, Integer> {

}