package edu.javeriana.touresbalon.reserva.controller;

import edu.javeriana.touresbalon.reserva.dto.ReservaRequest;
import edu.javeriana.touresbalon.reserva.dto.ReservaResponse;
import edu.javeriana.touresbalon.reserva.entities.Reserva;
import edu.javeriana.touresbalon.reserva.service.ReservaService;
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
@RequestMapping("/api/v1/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Operation(summary = "Crea una nueva reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva gudardada satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error guardando reserva",
                    content = @Content)})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearAlojamiento(@RequestBody ReservaRequest reservaRequest) {

        ReservaResponse result = reservaService.crearReserva(reservaRequest);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene una reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo el reserva satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo el reserva",
                    content = @Content)})
    @GetMapping(value = "/{reservaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarAlojamiento(@PathVariable @NotNull String reservaid) {

        Optional<Reserva> result = reservaService.consultarReserva(Integer.valueOf(reservaid));
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene lista de reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo lista de reserva satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reservas no encontrados",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo lista de reservas",
                    content = @Content)})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarListaAlojamientos() {

        Iterable<Reserva> result = reservaService.consultarListaReserva();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Elimina una reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva eliminada satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error eliminando reserva",
                    content = @Content)})
    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminarReserva(@RequestBody Reserva reserva) {

        reservaService.eliminarReserva(reserva);
        return ResponseEntity.ok("Alojamiento eliminado correctamente");
    }

}
