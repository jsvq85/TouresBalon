package edu.javeriana.touresbalon.proveedor.dto;

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
