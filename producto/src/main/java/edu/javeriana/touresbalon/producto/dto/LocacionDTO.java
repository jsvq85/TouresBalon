package edu.javeriana.touresbalon.producto.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocacionDTO {

    private int id;
    private String name;
    private Double price;

}