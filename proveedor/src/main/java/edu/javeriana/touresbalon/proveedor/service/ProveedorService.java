package edu.javeriana.touresbalon.proveedor.service;

import edu.javeriana.touresbalon.proveedor.entities.Proveedor;

import java.util.Optional;

public interface ProveedorService {

    Proveedor crearProveedor(Proveedor proveedor);

    void eliminarProveedor(Proveedor proveedor);

    Optional<Proveedor> consultarProveedor(int id);

    Iterable<Proveedor> consultarListaProveedores();
}
