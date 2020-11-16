package edu.javeriana.touresbalon.pago.entities;

import java.io.Serializable;

@Entity
@Table(name="LOCALIDAD_ESPECTACULO")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Data
@IdClass(LocalidadEspectaculo.class)
public class LocalidadEspectaculo implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idEspectaculo", referencedColumnName = "ID_PRODUCTO", nullable = false)
    private Espectaculo espectaculo;

    @Id
    @ManyToOne
    @JoinColumn(name = "idLocalidad", referencedColumnName = "ID_LOCALIDAD", nullable = false)
    private Localidad localidad;

    @Basic
    @Column(name = "PRECIO")
    private Double precio;

    @Basic
    @Column(name = "NOMBRE_LOCALIDAD")
    private String nombreLocalidad;


}
