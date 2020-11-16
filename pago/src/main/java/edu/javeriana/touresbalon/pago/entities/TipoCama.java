package edu.javeriana.touresbalon.pago.entities;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "TIPO_CAMA")
@Data
public class TipoCama {

    @Id
    @Column(name = "ID_TIPO_CAMA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idTipoCama;

    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
}
