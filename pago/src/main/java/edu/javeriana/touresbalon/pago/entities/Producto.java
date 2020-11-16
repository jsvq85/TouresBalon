package edu.javeriana.touresbalon.pago.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "PRODUCTO")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Data
public class Producto {
    @Id
    @Column(name = "ID_PRODUCTO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idProducto;

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
    @Column(name = "FECHA_REGISTO")
    private Timestamp fechaRegistro;

    @Basic
    @Column(name = "COMISION")
    private BigDecimal comision;

    @Column(name = "IMAGEN")
    private String imagen;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "PRODUCTO_RESERVA",
            joinColumns = {@JoinColumn(name = "ID_PRODUCTO")},
            inverseJoinColumns = {@JoinColumn(name = "ID_RESERVA")}
    )
    private List<Reserva> reservas;

    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="idProveedor", scope=Proveedor.class)
    @ManyToOne
    @JoinColumn(name = "idProveedor", referencedColumnName = "ID_PROVEEDOR")
    private Proveedor proveedor;


}
