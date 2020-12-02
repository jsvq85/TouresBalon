package edu.javeriana.touresbalon.reserva.client.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.javeriana.touresbalon.reserva.deserializer.impl.PagoResponseJSONDeserializerImpl;
import edu.javeriana.touresbalon.reserva.dto.PagoRequest;
import edu.javeriana.touresbalon.reserva.dto.PagoResponse;
import edu.javeriana.touresbalon.reserva.exceptions.ApiRetryException;
import edu.javeriana.touresbalon.reserva.kafka.KafkaProducer;
import edu.javeriana.touresbalon.reserva.client.PagoAPIClient;
import edu.javeriana.touresbalon.reserva.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class PagoAPIClientImpl implements PagoAPIClient {

    private static Logger LOGGER = LoggerFactory.getLogger(PagoAPIClientImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${service.api.pagos.url}")
    private String pagoServiceUrl;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public PagoResponse createPayment(PagoRequest pagoRequest){

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PagoResponse.class, new PagoResponseJSONDeserializerImpl())
                .create();

        String urlComplement = "/api/v1/pago";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(JsonConverter.toJSON(pagoRequest), headers);

        ResponseEntity<String> result = restTemplate.exchange(pagoServiceUrl + urlComplement, HttpMethod.POST, entity, String.class);

        if (result.getStatusCode().is2xxSuccessful()) {
            PagoResponse pagoResponse = gson.fromJson(result.getBody(), new TypeToken<PagoResponse>() {
            }.getType());
            log.trace("payment response mapped successfully");
            return pagoResponse;
        }  else if(result.getStatusCode().is5xxServerError()){
            throw new ApiRetryException("Error trying to perform payment");
        }
        return null;

    }

    @Override
    public PagoResponse compensatePayment(PagoRequest pagoRequest) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PagoResponse.class, new PagoResponseJSONDeserializerImpl())
                .create();

        String urlComplement = "/api/v1/pago";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(pagoServiceUrl + urlComplement, HttpMethod.PUT, entity, String.class);

        if (result.getStatusCode().is2xxSuccessful()) {
            PagoResponse pagoResponse = gson.fromJson(result.getBody(), new TypeToken<PagoResponse>() {
            }.getType());
            log.trace("compensation response mapped successfully");
            return pagoResponse;
        }  else if(result.getStatusCode().is5xxServerError()){
            throw new ApiRetryException("Error trying to perform compensation");
        }
        return null;
    }

    @Override
    public PagoResponse retryPaymentRecoveryFailure(RuntimeException t, PagoRequest pagoRequest) {
        LOGGER.info("Retry Recovery failure when trying to call user service - {}", t.getMessage());
        throw t;
    }


}
