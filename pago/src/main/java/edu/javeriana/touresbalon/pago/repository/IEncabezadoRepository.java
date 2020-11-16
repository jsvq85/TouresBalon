package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.entities.Encabezado;
import edu.javeriana.touresbalon.model.HeaderService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEncabezadoRepository extends CrudRepository<Encabezado, Integer> {

    @Query(name = "encabezadosQuery", nativeQuery = true)
    List<HeaderService> getHeaderByConfiguration(@Param("configurationId") Integer configurationId);
}
