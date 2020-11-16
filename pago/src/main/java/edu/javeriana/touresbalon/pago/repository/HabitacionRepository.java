package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.Habitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HabitacionRepository extends CrudRepository<Habitacion, Integer> {

}