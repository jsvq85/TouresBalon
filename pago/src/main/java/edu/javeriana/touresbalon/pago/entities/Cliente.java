package edu.javeriana.touresbalon.pago.entities;

import java.util.List;
import lombok.*;
import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "CLIENTE")
@Data
public class Cliente {

    @Id
    @Column(name = "ID_CLIENTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idCliente;

    @Basic
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic
    @Column(name = "APELLIDO")
    private String Apellido;

    @Basic
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    @Basic
    @Column(name = "PASAPORTE")
    private String pasaporte;

    @Basic
    @Column(name = "PAIS")
    private String pais;

    @Basic
    @Column(name = "CELULAR")
    private String celular;

    @Basic
    @Column(name = "TELEFONO")
    private String telefono;

    @Basic
    @Column(name = "DIRECCION")
    private String direccion;

    @Basic
    @Column(name = "CORREO")
    private String correo;

    @OneToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "ID_CATEGORIA", nullable = false)
    private Categoria categoria;

    @OneToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "idOrden", cascade = CascadeType.ALL)
    private List<Orden> ordenList;

}
