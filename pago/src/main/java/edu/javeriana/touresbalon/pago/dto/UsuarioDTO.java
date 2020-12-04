package edu.javeriana.touresbalon.pago.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class UsuarioDTO {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("lastname")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("categoria")
    private String categoria;

}
