package edu.javeriana.touresbalon.pago.entities;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "HABITACION")
@Data
public class Habitacion {

    @Id
    @Column(name = "ID_HABITACION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idHabitacion;
    
    @Basic
    @Column(name = "CANTIDAD_CAMA")
    private String cantidadCama;

    @OneToOne
    @JoinColumn(name = "idTipoHabitacion", referencedColumnName = "ID_TIPO_HABITACION", nullable = false)
    private TipoHabitacion tipoHabitacion;

    @OneToOne
    @JoinColumn(name = "idTipoCama", referencedColumnName = "ID_TIPO_CAMA", nullable = false)
    private TipoCama tipoCama;

}
