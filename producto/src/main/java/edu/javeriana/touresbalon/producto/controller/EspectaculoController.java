package edu.javeriana.touresbalon.producto.controller;

import edu.javeriana.touresbalon.producto.dto.DetailEspectaculoDTO;
import edu.javeriana.touresbalon.producto.dto.EspectaculoDTO;
import edu.javeriana.touresbalon.producto.entities.Espectaculo;
import edu.javeriana.touresbalon.producto.service.EspectaculoService;
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
import java.util.List;

@RestController
@RequestMapping("/api/v1/espectaculo")
public class EspectaculoController {

    @Autowired
    private EspectaculoService espectaculoService;

    @Operation(summary = "Guarda un nuevo espectaculo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Espectaculo gudardado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error guardando espectaculo",
                    content = @Content)})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearEspectaculo(@RequestBody Espectaculo espectaculo) {

        Espectaculo result = espectaculoService.crearEspectaculo(espectaculo);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene un espectaculo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo el espectaculo satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Espectaculo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo el espectaculo",
                    content = @Content)})
    @GetMapping(value = "/{espectaculoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarEspectaculo(@PathVariable @NotNull String espectaculoId) {

        DetailEspectaculoDTO result = espectaculoService.consultarEspectaculo(Integer.valueOf(espectaculoId));
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene lista de espectaculos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo lista de espectaculoes satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Espectaculos no encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo lista de espectaculos",
                    content = @Content)})
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarListaEspectaculos() {
        List<EspectaculoDTO> result = espectaculoService.consultarListaEspectaculos();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Elimina un espectaculo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Espectaculo eliminado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error eliminando espectaculo",
                    content = @Content)})
    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminarTransporte(@RequestBody Espectaculo espectaculo) {

        espectaculoService.eliminarEspectaculo(espectaculo);
        return ResponseEntity.ok("Espectaculo eliminado correctamente");
    }

}
