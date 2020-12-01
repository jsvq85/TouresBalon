package edu.javeriana.touresbalon.reserva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

  private Integer id;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @NotNull
  private String email;

}
