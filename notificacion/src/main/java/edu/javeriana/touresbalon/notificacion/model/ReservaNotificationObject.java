package edu.javeriana.touresbalon.notificacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservaNotificationObject {
  private Integer idUsuario;
  private String nombreUsuario;
  private String email;
  private Integer valor;
  private Integer nroTransaccion;
  private String mensaje;
}
