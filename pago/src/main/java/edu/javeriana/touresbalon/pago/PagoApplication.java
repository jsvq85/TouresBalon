package edu.javeriana.touresbalon.pago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRetry
public class PagoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagoApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(new FormHttpMessageConverter());
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return template;
    }

}
