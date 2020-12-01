package edu.javeriana.touresbalon.reserva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Integer id;
    private Integer providerId;
    private Integer number;
    private Long unitValue;
    private Long total;
}
