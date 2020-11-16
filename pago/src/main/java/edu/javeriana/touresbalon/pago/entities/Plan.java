package edu.javeriana.touresbalon.pago.entities;

import java.sql.Timestamp;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "PLAN")
@Data
public class Plan {

    @Id
    @Column(name = "ID_PLAN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idPlan;

    @Basic
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Basic
    @Column(name = "CANTIDAD")
    private int cantidad;

    @Basic
    @Column(name = "ESTADO")
    private boolean estado;

    @Basic
    @Column(name = "FECHA_INICIO")
    private Timestamp fechaInicio;

    @Basic
    @Column(name = "FECHA_FIN")
    private Timestamp fechaFin;

    @Basic
    @Column(name = "CIUDAD_ORIGEN")
    private String ciudadOrigen;

    @Basic
    @Column(name = "CIUDAD_DESTINO")
    private String ciudadDestino;

    @Basic
    @Column(name = "IMAGEN")
    private String imagen;


}
