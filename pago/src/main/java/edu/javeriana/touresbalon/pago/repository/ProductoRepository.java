package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}