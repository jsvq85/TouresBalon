package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.Transporte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransporteRepository extends CrudRepository<Transporte, Integer> {

}