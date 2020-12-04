package edu.javeriana.touresbalon.reserva.client.impl;

import com.google.gson.reflect.TypeToken;
import edu.javeriana.touresbalon.reserva.client.BRMAPIClient;
import edu.javeriana.touresbalon.reserva.dto.PagoRequest;
import edu.javeriana.touresbalon.reserva.dto.PagoResponse;
import edu.javeriana.touresbalon.reserva.exceptions.ApiRetryException;
import edu.javeriana.touresbalon.reserva.kafka.KafkaProducer;
import edu.javeriana.touresbalon.reserva.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Component
@Slf4j
public class BRMAPIClientImpl implements BRMAPIClient {

    private static Logger LOGGER = LoggerFactory.getLogger(BRMAPIClientImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${service.api.brm.url}")
    private String brmServiceUrl;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public boolean checkApproval(long monto, String tipo) {

        String urlComplement = "/validaorden";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(brmServiceUrl + urlComplement)
                .queryParam("monto", monto)
                .queryParam("tipo", tipo);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        if (result.getStatusCode().is2xxSuccessful()) {
            log.info("BRM rules validation result: " + Boolean.getBoolean(result.getBody()));
            return Boolean.valueOf(result.getBody());
        } else {
            throw new ApiRetryException("Error trying to perform BRM validation");
        }
    }

    @Override
    public PagoResponse retryPaymentRecoveryFailure(RuntimeException t, PagoRequest pagoRequest) {
        LOGGER.info("Retry Recovery failure when trying to call user service - {}", t.getMessage());
        throw t;
    }


}
