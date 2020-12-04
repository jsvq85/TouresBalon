package edu.javeriana.touresbalon.pago.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "PAGO")
@Data
public class Pago {

    @Id
    @Column(name = "ID_PAGO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idPago;

    @Basic
    @Column(name = "REFERENCIA")
    private long referencia;

    @Basic
    @Column(name = "VALOR")
    private long valor;

    @Basic
    @Column(name = "NRO_TRANSACCION")
    private long nroTransaccion;

    @Basic
    @Column(name = "FECHA_PAGO")
    private Timestamp fechaPago;

}
