package edu.javeriana.touresbalon.proveedor.entities;

import edu.javeriana.touresbalon.proveedor.dto.ConvenioDTO;
import edu.javeriana.touresbalon.proveedor.model.ConvenioObject;
import lombok.Data;

import javax.persistence.*;

@SqlResultSetMappings(value = {
        @SqlResultSetMapping(name = "convenioMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = ConvenioDTO.class,
                                columns = {
                                        @ColumnResult(name = "partnerId", type = Integer.class),
                                        @ColumnResult(name = "partnerName", type = String.class)
                                }
                        )
                }
        ),
        @SqlResultSetMapping(name = "convenioMapping2",
                classes = {
                        @ConstructorResult(
                                targetClass = ConvenioObject.class,
                                columns = {
                                        @ColumnResult(name = "idConvenio", type = Integer.class),
                                        @ColumnResult(name = "nombre", type = String.class),
                                        @ColumnResult(name = "descripcion", type = String.class)
                                }
                        )
                }
        )
})
@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "convenio.queryById",
                query = "Select partner_id as partnerId, partner_name as partnerName" +
                        "  From ingsoft.convenio" +
                        "  where partner_id = :partnerId",
                resultSetMapping = "convenioMapping"),
        @NamedNativeQuery(name = "convenio.queryByName",
                query = "Select partner_id as partnerId, partner_name as partnerName" +
                        "  From ingsoft.convenio" +
                        "  where coalesce(lower(partner_name), '') like concat('%', lower(:partnerName), '%')",
                resultSetMapping = "convenioMapping"),
        @NamedNativeQuery(name = "convenio2.queryById",
                query = "Select partner_id as idConvenio, partner_name as nombre, description as descripcion" +
                        "  From ingsoft.convenio" +
                        "  where partner_id = :partnerId",
                resultSetMapping = "convenioMapping2")
})

@Entity
@Table(name = "convenio")
@Data
public class Convenio {
    @Id
    @Column(name = "partner_id")
    private Integer partnerId;
    @Basic
    @Column(name = "partner_name")
    private String partnerName;
    @Basic
    @Column(name = "status")
    private Byte status;
    @Basic
    @Column(name = "description")
    private String description;
    /*@OneToMany(mappedBy = "convenio")
    private List<Configuracion> configuracionList;*/

}
