package edu.javeriana.touresbalon.producto.service;

import edu.javeriana.touresbalon.producto.entities.Producto;

import java.util.Optional;

public interface ProductoService {

    Producto crearProducto(Producto producto);

    void eliminarProducto(Producto producto);

    Optional<Producto> consultarProducto(int id);

    Iterable<Producto> consultarListaProducto();
}
