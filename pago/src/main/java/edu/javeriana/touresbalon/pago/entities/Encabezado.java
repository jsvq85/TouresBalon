package edu.javeriana.touresbalon.pago.entities;

import edu.javeriana.touresbalon.model.HeaderService;
import lombok.Data;

@SqlResultSetMapping(name = "encabezadoMapping",
        classes = {
                @ConstructorResult(
                        targetClass = HeaderService.class,
                        columns = {
                                @ColumnResult(name = "headerName", type = String.class),
                                @ColumnResult(name = "headerValue", type = String.class)
                        }
                )
        }
)

@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "encabezadosQuery",
                query = "Select nombre as headerName, valor as headerValue" +
                        " From ingsoft.encabezado" +
                        " Where configuracion_Id = :configurationId",
                resultSetMapping = "encabezadoMapping")
})

@Entity
@Table(name = "encabezado")
@Data
public class Encabezado {

    @Id
    @Column(name = "encabezado_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer encabezadoId;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "valor")
    private String valor;
    @OneToOne
    @JoinColumn(name = "configuracionId", referencedColumnName = "CONFIGURACION_ID", nullable = false)
    private Configuracion configuracion;

}
