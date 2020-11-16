package edu.javeriana.touresbalon.producto.repository;

import edu.javeriana.touresbalon.producto.entities.TipoHabitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoHabitacionRepository extends CrudRepository<TipoHabitacion, Integer> {

}