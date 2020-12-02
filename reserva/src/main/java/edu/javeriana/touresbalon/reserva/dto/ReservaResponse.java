package edu.javeriana.touresbalon.reserva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaResponse {

    private UsuarioDTO usuario;
    private PagoResponse pagoResponse;
    private List<ProductoDTO> productList;
    private String reservaStatus;
}
