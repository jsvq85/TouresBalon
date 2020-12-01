package edu.javeriana.touresbalon.reserva.repository;

import edu.javeriana.touresbalon.reserva.entities.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Integer> {

}