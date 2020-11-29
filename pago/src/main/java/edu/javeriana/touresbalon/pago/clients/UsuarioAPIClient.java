package edu.javeriana.touresbalon.pago.clients;

import edu.javeriana.touresbalon.pago.dto.UsuarioDTO;
import edu.javeriana.touresbalon.pago.exceptions.ApiRetryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

public interface UsuarioAPIClient {

    @Retryable( value = RuntimeException.class,
            maxAttempts = 3, backoff = @Backoff(delay = 20000))
    List<UsuarioDTO> getAllClients();

    @Retryable( value = RuntimeException.class,
            maxAttempts = 3, backoff = @Backoff(delay = 20000))
    UsuarioDTO getUsuarioById(int id);

    @Retryable( value = RuntimeException.class,
            maxAttempts = 3, backoff = @Backoff(delay = 20000))
    UsuarioDTO getUsuarioByTypeIdandId(String idType,int id);

    @Recover
    UsuarioDTO retryRecoveryFailure(RuntimeException t, int id);
}
