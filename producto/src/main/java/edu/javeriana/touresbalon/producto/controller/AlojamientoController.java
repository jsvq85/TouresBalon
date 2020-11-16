package edu.javeriana.touresbalon.producto.controller;

import edu.javeriana.touresbalon.producto.entities.Alojamiento;
import edu.javeriana.touresbalon.producto.service.AlojamientoService;
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
@RequestMapping("/api/v1/alojamiento")
public class AlojamientoController {

    @Autowired
    private AlojamientoService alojamientoService;

    @Operation(summary = "Guarda un nuevo alojamiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alojamiento gudardado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error guardando alojamiento",
                    content = @Content)})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearAlojamiento(@RequestBody Alojamiento alojamiento) {

        Alojamiento result = alojamientoService.crearAlojamiento(alojamiento);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene un alojamiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo el alojamiento satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Alojamiento no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo el alojamiento",
                    content = @Content)})
    @GetMapping(value = "/{alojamientoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarAlojamiento(@PathVariable @NotNull String alojamientoId) {

        Optional<Alojamiento> result = alojamientoService.consultarAlojamiento(Integer.valueOf(alojamientoId));
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene lista de alojamientos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo lista de alojamientos satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Alojamientos no encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo lista de alojamientos",
                    content = @Content)})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarListaAlojamientos() {

        Iterable<Alojamiento> result = alojamientoService.consultarListaAlojamientos();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Elimina un alojamiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alojamiento eliminado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error eliminando alojamiento",
                    content = @Content)})
    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminarAlojamiento(@RequestBody Alojamiento alojamiento) {

        alojamientoService.eliminarAlojamiento(alojamiento);
        return ResponseEntity.ok("Alojamiento eliminado correctamente");
    }

}
