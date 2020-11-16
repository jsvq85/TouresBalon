package edu.javeriana.touresbalon.producto.repository;

import edu.javeriana.touresbalon.producto.entities.Habitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HabitacionRepository extends CrudRepository<Habitacion, Integer> {

}