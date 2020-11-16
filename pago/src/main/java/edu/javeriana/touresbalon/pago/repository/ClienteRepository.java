package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

}