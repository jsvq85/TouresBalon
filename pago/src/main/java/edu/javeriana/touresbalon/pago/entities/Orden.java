package edu.javeriana.touresbalon.pago.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "ORDEN")
@Data
public class Orden {

    @Id
    @Column(name = "ID_ORDEN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idOrden;

    @Basic
    @Column(name = "FECHA_REGISTRO")
    private Timestamp fechaRegistro;

    @Basic
    @Column(name = "ESTADO")
    private boolean estado;

    @Basic
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "ID_CLIENTE", nullable = false)
    private Cliente cliente;

}
