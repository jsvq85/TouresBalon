package edu.javeriana.touresbalon.producto.service.impl;

import edu.javeriana.touresbalon.producto.entities.Producto;
import edu.javeriana.touresbalon.producto.exceptions.NotFoundException;
import edu.javeriana.touresbalon.producto.repository.ProductoRepository;
import edu.javeriana.touresbalon.producto.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto crearProducto(Producto producto) {

        productoRepository.save(producto);

        return producto;
    }
    @Override
    public void eliminarProducto(Producto producto) {

        productoRepository.delete(producto);

    }

    @Override
    public Optional<Producto> consultarProducto(int id) {

        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isEmpty())
            throw new NotFoundException("Producto no encontrado");
        return producto;

    }

    @Override
    public Iterable<Producto> consultarListaProducto() {
        Iterable<Producto> listaProductos = productoRepository.findAll();
        if(!listaProductos.iterator().hasNext())
            throw new NotFoundException("No hay productos en la lista");
        return listaProductos;
    }

}
