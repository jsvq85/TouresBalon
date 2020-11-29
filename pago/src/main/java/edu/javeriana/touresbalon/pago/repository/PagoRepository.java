package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.pago.entities.Pago;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PagoRepository extends CrudRepository<Pago, Integer> {

}