package edu.javeriana.touresbalon.pago.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioObject {

  private Integer id;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @NotNull
  private String email;

}
