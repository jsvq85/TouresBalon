package edu.javeriana.touresbalon.producto.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class DetailEspectaculoDTO {

    private int id;
    private String image;
    private String imageLocalities;
    private String name;
    private String description;
    private String place;
    private String city;
    private Date date;
    private String category;
    private long capacity;
    private List<LocacionDTO> localities;

}
