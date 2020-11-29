package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.pago.entities.Orden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdenRepository extends CrudRepository<Orden, Integer> {

}