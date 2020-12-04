package edu.javeriana.touresbalon.reserva.entities;

import java.sql.Timestamp;
import java.util.List;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "RESERVA")
@Data
public class Reserva {

    @Id
    @Column(name = "ID_RESERVA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idReserva;

    @Basic
    @Column(name = "FECHA_REGISTRO")
    private Timestamp fechaRegistro;

    @Basic
    @Column(name = "ESTADO")
    private String estado;

    @Basic
    @Column(name = "COMENTARIOS")
    private String comentarios;

    @OneToMany(mappedBy = "reserva")
    private List<Producto> productos;

    @Basic
    @Column(name = "ID_PAGO")
    private long idPago;

    @Basic
    @Column(name = "VALOR")
    private long valor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private Usuario usuario;


}
