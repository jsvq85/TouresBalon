package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.pago.entities.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Integer> {

}