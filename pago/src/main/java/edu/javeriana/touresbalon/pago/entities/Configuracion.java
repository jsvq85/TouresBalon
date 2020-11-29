package edu.javeriana.touresbalon.pago.entities;

import edu.javeriana.touresbalon.pago.model.ServiceDescription;
import lombok.*;
import javax.persistence.*;
import lombok.Data;

@SqlResultSetMapping(name = "configurationMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ServiceDescription.class,
                        columns = {
                                @ColumnResult(name = "configurationId", type = Integer.class),
                                @ColumnResult(name = "serviceType", type = String.class),
                                @ColumnResult(name = "serviceUrl", type = String.class),
                                @ColumnResult(name = "operation", type = String.class),
                                @ColumnResult(name = "method", type = String.class),
                                @ColumnResult(name = "requestPayload", type = String.class),
                                @ColumnResult(name = "referencePath", type = String.class),
                                @ColumnResult(name = "valuePath", type = String.class),
                                @ColumnResult(name = "messagePath", type = String.class),
                        }
                )
        }
)


@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "configurationQuery",
                query = "Select configuracion_id as configurationId, tipo_servicio as serviceType, url as serviceUrl, operacion as operation, metodo as method," +
                        "       payload as requestPayload, reference_path as referencePath, valor_path as valuePath," +
                        "       mensaje_path as messagePath" +
                        "  From ingsoft.configuracion" +
                        "  Where id_proveedor = :partnerId",
                resultSetMapping = "configurationMapping")
})


@Entity
@Table(name = "configuracion")
@Data
public class Configuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configuracion_id")
    private Integer configuracionId;
    @Basic
    @Column(name = "tipo_servicio")
    private String tipoServicio;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "operacion")
    private String operacion;
    @Basic
    @Column(name = "metodo")
    private String metodo;
    @Basic
    @Column(name = "payload")
    private String payload;
    @Basic
    @Column(name = "reference_path")
    private String referencePath;
    @Basic
    @Column(name = "valor_path")
    private String valorPath;
    @Basic
    @Column(name = "mensaje_path")
    private String mensajePath;
    @ManyToOne
    @JoinColumn(name = "idProveedor", referencedColumnName = "ID_PROVEEDOR")
    private Proveedor proveedor;
    /*@ManyToOne
    @JoinColumn(name = "idConvenio", referencedColumnName = "partner_id")
    private Convenio convenio;*/
}
