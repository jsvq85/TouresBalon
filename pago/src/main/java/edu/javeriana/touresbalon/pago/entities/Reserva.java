package edu.javeriana.touresbalon.pago.entities;

import java.sql.Timestamp;
import java.util.List;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
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
    @Column(name = "TIPO_RESERVA")
    private String tipoReserva;

    @Basic
    @Column(name = "FECHA_REGISTRO")
    private Timestamp fechaRegistro;

    @Basic
    @Column(name = "FECHA_INICIO")
    private Timestamp fechaInicio;

    @Basic
    @Column(name = "FECHA_FIN")
    private Timestamp fechaFin;

    @Basic
    @Column(name = "ESTADO")
    private boolean estado;

    @Basic
    @Column(name = "NRO_ADULTOS")
    private Integer nroAdultos;

    @Basic
    @Column(name = "NRO_MENORES")
    private Integer nroMenores;

    @Basic
    @Column(name = "COMENTARIOS")
    private String comentarios;

    @ManyToMany(mappedBy = "reservas")
    private List<Producto> productos;

}
