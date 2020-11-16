package edu.javeriana.touresbalon.pago.entities;


@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "USUARIO")
@Data
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idUsuario;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Basic
    @Column(name = "EMAIL")
    private String eMail;

    @Basic
    @Column(name = "ACTIVO")
    private boolean activo;

    @Basic
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic
    @Column(name = "APELLIDO")
    private String apellido;

    @Basic
    @Column(name = "DIRECCION")
    private String direccion;

    @Basic
    @Column(name = "TELEFONO")
    private String telefono;

    @Basic
    @Column(name = "CIUDAD")
    private String ciudad;

    @Basic
    @Column(name = "DEPARTAMENTO")
    private String departamento;

    @Basic
    @Column(name = "PAIS")
    private String pais;




}
