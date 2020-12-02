package edu.javeriana.touresbalon.notificacion.kafka;

import edu.javeriana.touresbalon.notificacion.model.NotificationObject;
import edu.javeriana.touresbalon.notificacion.utils.JsonConverter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class KafkaClient {

  @KafkaListener(topics = "${touresbalon.notificaciones.topic.name}", groupId = "${touresbalon.notificaciones.group.name}")
  public void listenNotifications(String message) {
    System.out.println("Mensaje Recibido " + message);
    NotificationObject data = JsonConverter.toObject(message, NotificationObject.class);
    sendMailPago(data);
  }


  public void sendMailPago(NotificationObject data) {
    System.out.println("Preparado para enviar correo");
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.office365.com");
    props.put("mail.smtp.socketFactory.port", "587");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth","true");
    props.put("mail.smtp.port","587");
    props.put("mail.smtp.starttls.enable","true");
    System.out.println("Puse las propiedades");
    Session session = Session.getDefaultInstance(
            props,
            new Authenticator() {
              @Override
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jsvillanueva@javeriana.edu.co", "");
              }
            }
    );

    try {
      System.out.println("Armando el mensaje");
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("jsvillanueva@javeriana.edu.co"));
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(data.getEmail()));
      message.setSubject("Notificación de Pago de Factura");
      message.setText("Señor@: " + data.getNombreUsuario() + " Le informamos que la transacción de su factura nro " + data.getReferencia() + " por valor de " + data.getValor()
                      + " tuvo el siguiente resultado " + data.getMensaje());
      Transport.send(message);
      System.out.println("Correo enviado.");
    } catch (MessagingException e) {
      e.printStackTrace();
    }


  }

}
