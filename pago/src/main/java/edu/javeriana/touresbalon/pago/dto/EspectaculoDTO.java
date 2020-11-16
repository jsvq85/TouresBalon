package edu.javeriana.touresbalon.pago.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EspectaculoDTO {

    private int id;
    private String image;
    private String name;
    private String description;
    private String category;
    private List<Double> price;

}
