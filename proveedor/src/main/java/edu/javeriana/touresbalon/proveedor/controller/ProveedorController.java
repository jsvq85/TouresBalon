package edu.javeriana.touresbalon.proveedor.controller;

import edu.javeriana.touresbalon.proveedor.entities.Proveedor;
import edu.javeriana.touresbalon.proveedor.service.ProveedorService;
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
@RequestMapping("/api/v1/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Operation(summary = "Guarda un nuevo proveedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor gudardado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error guardando el proveedor",
                    content = @Content)})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearProveedor(@RequestBody Proveedor proveedor) {

        Proveedor result = proveedorService.crearProveedor(proveedor);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene un proveedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo el proveedor satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo el proveedor",
                    content = @Content)})
    @GetMapping(value = "/{proveedorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarProveedor(@PathVariable @NotNull String proveedorId) {

        Optional<Proveedor> result = proveedorService.consultarProveedor(Integer.valueOf(proveedorId));
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene lista de proveedores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo lista de proveedores satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Proveedores no encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo lista de proveedores",
                    content = @Content)})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarListaProveedores() {

        Iterable<Proveedor> result = proveedorService.consultarListaProveedores();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Elimina un proveedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor eliminado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error eliminando el proveedor",
                    content = @Content)})
    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminarProveedor(@RequestBody Proveedor proveedor) {

        proveedorService.eliminarProveedor(proveedor);
        return ResponseEntity.ok("Proveedor eliminado correctamente");
    }

}
