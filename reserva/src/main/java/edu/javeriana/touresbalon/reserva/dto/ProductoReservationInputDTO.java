package edu.javeriana.touresbalon.reserva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoReservationInputDTO {

    private String id;
    private Integer productId;
    private Integer providerId;
    private String type;
}