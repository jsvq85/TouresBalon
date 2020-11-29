package edu.javeriana.touresbalon.pago.repository;

import edu.javeriana.touresbalon.pago.dto.ConvenioDTO;
import edu.javeriana.touresbalon.pago.entities.Convenio;
import edu.javeriana.touresbalon.pago.model.ConvenioObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConvenioRepository extends CrudRepository<Convenio, Integer> {

    @Query(name = "convenio.queryById", nativeQuery = true)
    Optional<ConvenioDTO> getConvenioById(@Param("partnerId") Integer partnerId);

    @Query(name = "convenio.queryByName", nativeQuery = true)
    List<ConvenioDTO> getConvenioByName(@Param("partnerName") String partnerName);

    @Query(name = "convenio2.queryById", nativeQuery = true)
    Optional<ConvenioObject> getConvenioById2(@Param("partnerId") Integer partnerId);
}
