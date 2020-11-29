package edu.javeriana.touresbalon.notificacion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificacion")
public class NotificacionController {

  @GetMapping
  public String testService() {
    return "Notification service running";
  }
}
