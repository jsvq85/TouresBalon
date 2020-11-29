package edu.javeriana.touresbalon.producto.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class ProveedorClient {

    @Autowired
    private RestTemplate restTemplate;

    /*@HystrixCommand(fallbackMethod="retrieveFallbackPing")
    public PingResponse pingSadr(PingRequest pingRequest){
        return restTemplate.postForObject(pingUrl, pingRequest, PingResponse.class);
    }

    public PingResponse retrieveFallbackPingSadr(PingRequest pingRequest){
        return new PingResponse("Error pinging sadr. This is a fallback message");
    }*/

}
