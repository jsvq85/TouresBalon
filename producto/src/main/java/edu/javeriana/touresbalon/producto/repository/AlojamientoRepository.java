package edu.javeriana.touresbalon.producto.repository;

import edu.javeriana.touresbalon.producto.entities.Alojamiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlojamientoRepository extends CrudRepository<Alojamiento, Integer> {

}