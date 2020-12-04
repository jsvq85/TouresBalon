package edu.javeriana.touresbalon.pago.controller;

import edu.javeriana.touresbalon.pago.model.PagoRequest;
import edu.javeriana.touresbalon.pago.model.PagoResponse;
import edu.javeriana.touresbalon.pago.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pago")
public class PagoController {

    @Autowired
    private final PagoService ps;

    @Autowired
    public PagoController(PagoService ps) {
        this.ps = ps;
    }

    @Operation(summary = "Obtiene los valores de un pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo el valor del pago satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "pago no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error obteniendo el pago",
                    content = @Content)})
    @GetMapping
    public PagoResponse consultarPago(@RequestParam(value = "referencia") long referencia) {
        return ps.consultarPago(referencia);
    }

    @Operation(summary = "Genera un pago de factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago realizado satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error realizando pago",
                    content = @Content)})
    @PostMapping
    public PagoResponse pagarFactura(@RequestBody PagoRequest data) {
        return ps.pagarFactura(data);
    }

    @Operation(summary = "Compensa un pago de factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura compensada satisfactoriamente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error compensando factura",
                    content = @Content)})
    @PutMapping
    public PagoResponse compensarFactura(@RequestBody PagoRequest data) {
        return ps.compensarFactura(data);
    }

}
