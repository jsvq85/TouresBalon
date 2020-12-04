package edu.javeriana.touresbalon.reserva.client;

import edu.javeriana.touresbalon.reserva.dto.PagoRequest;
import edu.javeriana.touresbalon.reserva.dto.PagoResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface BRMAPIClient {


    @Retryable( value = RuntimeException.class,
            maxAttempts = 3, backoff = @Backoff(delay = 5000))
    boolean checkApproval(long monto, String tipo);

    @Recover
    PagoResponse retryPaymentRecoveryFailure(RuntimeException t, PagoRequest pagoRequest);
}
