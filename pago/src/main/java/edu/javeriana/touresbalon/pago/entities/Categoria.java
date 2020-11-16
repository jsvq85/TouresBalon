package edu.javeriana.touresbalon.pago.entities;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "CATEGORIA")
@Data
public class Categoria {

    @Id
    @Column(name = "ID_CATEGORIA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idCategoria;

    @Basic
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic
    @Column(name = "DESCRIPCION")
    private String descripcion;

}
