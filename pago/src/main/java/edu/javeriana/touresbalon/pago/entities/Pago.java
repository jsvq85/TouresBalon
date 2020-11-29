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
    @Column(name = "ID_FACTURA")
    private int idFactura;

    @Basic
    @Column(name = "PAGO_TOTAL")
    private BigDecimal pagoTotal;

    @Basic
    @Column(name = "PAIS")
    private int idPais;

    @Basic
    @Column(name = "IMPUESTO")
    private BigDecimal impuesto;

    @Basic
    @Column(name = "FECHA_PAGO")
    private Timestamp fechaPago;

    @Basic
    @Column(name = "FECHA_LIMITE_PAGO")
    private Timestamp fechaLimitePago;

}
