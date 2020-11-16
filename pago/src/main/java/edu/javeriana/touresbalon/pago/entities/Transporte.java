package edu.javeriana.touresbalon.pago.entities;

@Entity
@Table(name="TRANSPORTE")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Data
public class Transporte extends Producto {

    @Basic
    @Column(name = "NUMERO_TRANSPORTE")
    private String numeroTransporte;

    @Basic
    @Column(name = "CIUDAD_ORIGEN")
    private String ciudadOrigen;

    @Basic
    @Column(name = "CIUDAD_DESTINO")
    private String ciudadDestino;

    @OneToOne
    @JoinColumn(name = "idTipoTransporte", referencedColumnName = "ID_TIPO_TRANSPORTE")
    private TipoTransporte tipoTransporte;
}
