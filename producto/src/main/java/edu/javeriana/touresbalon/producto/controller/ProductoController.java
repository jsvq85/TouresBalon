package edu.javeriana.touresbalon.producto.controller;

import edu.javeriana.touresbalon.producto.entities.Producto;
import edu.javeriana.touresbalon.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {


    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Guarda un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto gudardado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error guardando el producto",
                    content = @Content)})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {

        Producto result = productoService.crearProducto(producto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo el producto satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo el producto",
                    content = @Content)})
    @GetMapping(value = "/{productoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarProducto(@PathVariable @NotNull String productoId) {

        Optional<Producto> result = productoService.consultarProducto(Integer.valueOf(productoId));
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene lista de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo lista de productos satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Productos no encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo lista de productos",
                    content = @Content)})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarListaProductos() {

        Iterable<Producto> result = productoService.consultarListaProducto();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Elimina un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error eliminando el producto",
                    content = @Content)})
    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminarProducto(@RequestBody Producto producto) {

        productoService.eliminarProducto(producto);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }

}
