package edu.javeriana.touresbalon.producto.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ESPECTACULO")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Data
public class Espectaculo extends Producto {

    @Basic
    @Column(name = "CIUDAD")
    private String ciudad;

    @Basic
    @Column(name = "LUGAR")
    private String lugar;

    @OneToOne
    @JoinColumn(name = "idTipoEspectaculo", referencedColumnName = "ID_TIPO_ESPECTACULO", nullable = false)
    private TipoEspectaculo tipoEspectaculo;

    @Basic
    @Column(name = "FECHA")
    private Timestamp fecha;

    @Basic
    @Column(name = "AFORO")
    private Long aforo;

    @Basic
    @Column(name = "IMAGEN_LUGAR")
    private String imagenLugar;

}
