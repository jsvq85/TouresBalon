package edu.javeriana.touresbalon.producto.repository;

import edu.javeriana.touresbalon.producto.entities.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}