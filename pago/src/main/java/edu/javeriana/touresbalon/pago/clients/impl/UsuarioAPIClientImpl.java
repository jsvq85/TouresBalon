package edu.javeriana.touresbalon.pago.clients.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.javeriana.touresbalon.pago.clients.UsuarioAPIClient;
import edu.javeriana.touresbalon.pago.deserializer.impl.UsuarioJSONDeserializerImpl;
import edu.javeriana.touresbalon.pago.dto.UsuarioDTO;
import edu.javeriana.touresbalon.pago.exceptions.ApiRetryException;
import edu.javeriana.touresbalon.pago.exceptions.RestClientException;
import edu.javeriana.touresbalon.pago.kafka.KafkaProducer;
import edu.javeriana.touresbalon.pago.model.NotificationObject;
import edu.javeriana.touresbalon.pago.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class UsuarioAPIClientImpl implements UsuarioAPIClient {

    private static Logger LOGGER = LoggerFactory.getLogger(UsuarioAPIClientImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${service.api.usuarios.url}")
    private String usuarioServiceUrl;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public List<UsuarioDTO> getAllClients() {
        List<UsuarioDTO> usuarioList = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(List.class, new UsuarioJSONDeserializerImpl())
                .create();

        String urlComplement = "/api/v1/structures/getAllByStructure?id=";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(usuarioServiceUrl + urlComplement, HttpMethod.GET, entity, String.class);

        usuarioList = gson.fromJson(result.getBody(), new TypeToken<List<UsuarioDTO>>() {
        }.getType());

        if (usuarioList == null) {
            throw new RestClientException(String.format("No users returned from users api call to url %s", usuarioServiceUrl), HttpStatus.NOT_FOUND);
        }

        return usuarioList;

    }

    @Override
    public UsuarioDTO getUsuarioById(int id){

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(UsuarioDTO.class, new UsuarioJSONDeserializerImpl())
                .create();

        String urlComplement = "/api/v1/client/getClientById?id=";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(usuarioServiceUrl + urlComplement + id, HttpMethod.GET, entity, String.class);

        if (result.getStatusCode().is2xxSuccessful()) {
            UsuarioDTO usuarioDTO = gson.fromJson(result.getBody(), new TypeToken<UsuarioDTO>() {
            }.getType());
            log.trace("Usuario mapped successfully");
            return usuarioDTO;
        } else if(result.getStatusCode().is4xxClientError()){
            log.trace("Usuario {} was not found.", id);
            return null;
        } else if(result.getStatusCode().is5xxServerError()){
            throw new ApiRetryException("Error trying to contact client service");
        }
        return null;

    }

    @Override
    public UsuarioDTO getUsuarioByTypeIdandId(String idType, int id) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(UsuarioDTO.class, new UsuarioJSONDeserializerImpl())
                .create();

        String urlComplement = "/api/v1/client/getClientById?id=";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(usuarioServiceUrl + urlComplement + id, HttpMethod.GET, entity, String.class);

        if (result.getStatusCode().is2xxSuccessful()) {
            UsuarioDTO usuarioDTO = gson.fromJson(result.getBody(), new TypeToken<UsuarioDTO>() {
            }.getType());
            log.trace("Usuario mapped successfully");
            return usuarioDTO;
        } else {
            log.trace("Usuario {} was not found.", id);
            return null;
        }
    }

    @Override
    public UsuarioDTO retryRecoveryFailure(RuntimeException t, int id) {
        LOGGER.info("Retry Recovery failure when trying to call user service - {}", t.getMessage());
        throw t;
    }


}
