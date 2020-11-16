package edu.javeriana.touresbalon.proveedor.repository;

import edu.javeriana.touresbalon.proveedor.entities.Proveedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {

}