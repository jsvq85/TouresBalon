package edu.javeriana.touresbalon.reserva.repository;

import edu.javeriana.touresbalon.reserva.entities.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

    Producto findByIdProductoAndIdProveedor(int idProducto, int idProveedor);

    List<Producto> findByReserva_IdReservaAndAndEstado(int idReserva, String estado);

}