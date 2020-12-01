package edu.javeriana.touresbalon.reserva.client;

import edu.javeriana.touresbalon.reserva.dto.PagoRequest;
import edu.javeriana.touresbalon.reserva.dto.PagoResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

public interface PagoAPIClient {


    @Retryable( value = RuntimeException.class,
            maxAttempts = 3, backoff = @Backoff(delay = 5000))
    PagoResponse createPayment(PagoRequest pagoRequest);

    @Retryable( value = RuntimeException.class,
            maxAttempts = 3, backoff = @Backoff(delay = 5000))
    PagoResponse compensatePayment(PagoRequest pagoRequest);

    @Recover
    PagoResponse retryPaymentRecoveryFailure(RuntimeException t, PagoRequest pagoRequest);
}
