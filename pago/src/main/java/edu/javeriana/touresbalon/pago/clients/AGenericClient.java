package edu.javeriana.touresbalon.pago.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.javeriana.touresbalon.pago.model.HeaderService;
import edu.javeriana.touresbalon.pago.model.PagoResponse;
import edu.javeriana.touresbalon.pago.model.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public abstract class AGenericClient {

    private static Logger LOGGER = LoggerFactory.getLogger(AGenericClient.class);

    protected HttpHeaders getHeaders(List<HeaderService> headers) {
        HttpHeaders rta = new HttpHeaders();
        headers.forEach(h -> rta.add(h.getHeaderName(), h.getHeaderValue()));
        return rta;
    }

    public String processPath(JsonNode data, String path) {
        if (data == null || path == null || path.equals("")) {
            return null;
        }
        String[] aux = path.split(Pattern.quote("."));
        JsonNode node = data.path(aux[0]);
        if (aux.length == 1) {
            return node.asText();
        } else {
            return processPath(node, String.join(".", Arrays.copyOfRange(aux, 1, aux.length)));
        }
    }

    protected PagoResponse processRta(ObjectMapper mapper, String data, String pathReference, String pathValue, String pathMessage) {
        PagoResponse result = new PagoResponse();
        try {
            JsonNode rootNode = mapper.readTree(data);
            result.setReferencia(processPath(rootNode, pathReference));
            result.setValor(processPath(rootNode, pathValue));
            result.setMensaje(processPath(rootNode, pathMessage));
            result.setNroTransaccion(processPath(rootNode, "nroTransaccion"));
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Retryable(value = RuntimeException.class,
            maxAttempts = 3, backoff = @Backoff(delay = 20000))
    public abstract PagoResponse callService(ServiceDescription info);

    @Recover
    public PagoResponse retryRecoveryFailure(RuntimeException t, ServiceDescription info) {
        LOGGER.info("Retry Recovery failure when trying to call user service - {}", t.getMessage());
        throw t;
    }
}
