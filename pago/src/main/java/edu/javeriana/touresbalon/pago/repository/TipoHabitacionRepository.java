package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.TipoHabitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoHabitacionRepository extends CrudRepository<TipoHabitacion, Integer> {

}