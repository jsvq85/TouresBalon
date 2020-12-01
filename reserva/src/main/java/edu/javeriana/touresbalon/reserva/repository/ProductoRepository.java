package edu.javeriana.touresbalon.reserva.repository;

import edu.javeriana.touresbalon.producto.entities.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}