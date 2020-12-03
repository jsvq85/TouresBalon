package edu.javeriana.touresbalon.reserva.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${touresbalon.proveedores.topic.name.output}")
    private String proveeoresTopicName;
    @Value(value = "${touresbalon.notificaciones.topic.name}")
    private String notificacionesTopicName;

    public void sendProveedoresMessage(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(proveeoresTopicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Error publicando la notificación");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Notificación publicada " + message + " - " + result);
            }
        });
        this.kafkaTemplate.send(proveeoresTopicName, message);
    }

    public void sendNotificacionesMessage(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(notificacionesTopicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Error publicando la notificación");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Notificación publicada " + message + " - " + result);
            }
        });
        this.kafkaTemplate.send(notificacionesTopicName, message);
    }


}
