package edu.javeriana.touresbalon.producto.controller;

import edu.javeriana.touresbalon.producto.entities.Transporte;
import edu.javeriana.touresbalon.producto.service.TransporteService;
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
@RequestMapping("/api/v1/transporte")
public class TransporteController {

    @Autowired
    private TransporteService transporteService;

    @Operation(summary = "Guarda un nuevo transporte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transporte gudardado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error guardando transporte",
                    content = @Content)})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearTransporte(@RequestBody Transporte transporte) {

        Transporte result = transporteService.crearTransporte(transporte);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene un transporte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo el transporte satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Transporte no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo el transporte",
                    content = @Content)})
    @GetMapping(value = "/{transporteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarProducto(@PathVariable @NotNull String transporteId) {

        Optional<Transporte> result = transporteService.consultarTransporte(Integer.valueOf(transporteId));
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene lista de transportes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo lista de transportes satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Transportes no encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo lista de transportes",
                    content = @Content)})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarListaTransportes() {

        Iterable<Transporte> result = transporteService.consultarListaTransportes();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Elimina un transporte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transporte eliminado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error eliminando transporte",
                    content = @Content)})
    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminarTransporte(@RequestBody Transporte transporte) {

        transporteService.eliminarTransporte(transporte);
        return ResponseEntity.ok("Transporte eliminado correctamente");
    }

}
