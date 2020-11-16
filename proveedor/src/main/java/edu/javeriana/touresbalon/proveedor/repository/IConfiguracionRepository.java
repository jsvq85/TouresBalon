package edu.javeriana.touresbalon.proveedor.repository;

import edu.javeriana.touresbalon.proveedor.entities.Configuracion;
import edu.javeriana.touresbalon.proveedor.model.ServiceDescription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConfiguracionRepository extends CrudRepository<Configuracion, Integer> {

    @Query(name = "configurationQuery", nativeQuery = true)
    List<ServiceDescription> getConfigByConvenio(@Param("partnerId") Integer partnerId);
}
