package edu.javeriana.touresbalon.pago.entities;

import java.math.BigDecimal;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "TIPO_TRANSPORTE")
@Data
public class TipoTransporte {

    @Id
    @Column(name = "ID_TIPO_TRANSPORTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idTipoTransporte;

    @Basic
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic
    @Column(name = "IMPUESTO")
    private BigDecimal impuesto;
}
