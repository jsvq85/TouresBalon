package edu.javeriana.touresbalon.pago.clients;

import edu.javeriana.touresbalon.pago.entities.Proveedor;
import edu.javeriana.touresbalon.pago.entities.Usuario;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

public interface ProveedorAPIClient {

    List<Proveedor> getAllProveedor();

    @Retryable( value = Exception.class,
            maxAttempts = 10, backoff = @Backoff(delay = 30000))
    Proveedor getProveedorById(String id);

    @Retryable( value = Exception.class,
            maxAttempts = 10, backoff = @Backoff(delay = 30000))
    Usuario getProveedorByTypeIdandId(String idType,String id);

}
