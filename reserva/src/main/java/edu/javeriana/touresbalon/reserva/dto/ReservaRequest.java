package edu.javeriana.touresbalon.reserva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequest {

    private UsuarioDTO usuario;
    private PaymentDTO payment;
    private List<ProductoDTO> productList;
    private Integer referencia;
    private Integer total;
}
