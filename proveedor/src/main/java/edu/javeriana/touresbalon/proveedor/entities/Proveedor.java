package edu.javeriana.touresbalon.proveedor.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "PROVEEDOR")
@Data
public class Proveedor {

    @Id
    @Column(name = "ID_PROVEEDOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idProveedor;

    @Basic
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic
    @Column(name = "FECHA_REGISTRO")
    private Timestamp fechaRegistro;

    @Basic
    @Column(name = "ID_CUENTA")
    private String idCuenta;

    @Basic
    @Column(name = "ID_BANCO")
    private String idBanco;

    @Basic
    @Column(name = "ACTIVO")
    private boolean activo;

    @OneToMany(mappedBy = "proveedor")
    private List<Configuracion> configuracionList;

}
