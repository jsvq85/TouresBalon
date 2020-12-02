package edu.javeriana.touresbalon.reserva.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "PRODUCTO")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Data
public class Producto {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "ID_PRODUCTO")
    private int idProducto;

    @Column(name = "ID_PROVEEDOR")
    private int idProveedor;

    @ManyToOne
    @JoinColumn(name = "idReserva", referencedColumnName = "ID_RESERVA", nullable = false)
    private Reserva reserva;

}
