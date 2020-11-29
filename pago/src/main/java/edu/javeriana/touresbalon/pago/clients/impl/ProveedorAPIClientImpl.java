package edu.javeriana.touresbalon.pago.clients.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.javeriana.touresbalon.pago.clients.ProveedorAPIClient;
import edu.javeriana.touresbalon.pago.clients.UsuarioAPIClient;
import edu.javeriana.touresbalon.pago.entities.Proveedor;
import edu.javeriana.touresbalon.pago.entities.Usuario;
import lombok.extern.slf4j.Slf4j;
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
public class ProveedorAPIClientImpl implements ProveedorAPIClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service.api.usuarios.url}")
    private String proveedorServiceUrl;


    @Override
    public List<Proveedor> getAllProveedor() {
        return null;
    }

    @Override
    public Proveedor getProveedorById(String id) {
        return null;
    }

    @Override
    public Usuario getProveedorByTypeIdandId(String idType, String id) {
        return null;
    }
}
