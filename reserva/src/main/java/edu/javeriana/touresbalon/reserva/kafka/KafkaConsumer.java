package edu.javeriana.touresbalon.reserva.kafka;

import edu.javeriana.touresbalon.reserva.dto.NotificationObject;
import edu.javeriana.touresbalon.reserva.dto.ProductoReservationInputDTO;
import edu.javeriana.touresbalon.reserva.entities.Producto;
import edu.javeriana.touresbalon.reserva.entities.Reserva;
import edu.javeriana.touresbalon.reserva.repository.ProductoRepository;
import edu.javeriana.touresbalon.reserva.repository.ReservaRepository;
import edu.javeriana.touresbalon.reserva.service.ReservaService;
import edu.javeriana.touresbalon.reserva.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableKafka
public class KafkaConsumer {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private ReservaService reservaService;

    @KafkaListener(topics = "${touresbalon.proveedores.topic.name.input}", groupId = "${touresbalon.proveedores.group.name.input}")
    public void listenProviderNotification(String message) {

        ProductoReservationInputDTO data = JsonConverter.toObject(message, ProductoReservationInputDTO.class);
        if (data.getType().equals("COMMAND_CREATE_RESERVATION")) {
            Producto producto = productoRepository.findByIdProductoAndIdProveedor(data.getProductId(), data.getProviderId());
            producto.setEstado("CONFIRMED");
            productoRepository.save(producto);
            confirmarReserva(producto);
        } else if (data.getType().equals("COMMAND_FAILED_RESERVATION")) {
            Producto producto = productoRepository.findByIdProductoAndIdProveedor(data.getProductId(), data.getProviderId());
            reservaService.eliminarReserva(producto.getReserva());
        }
    }

    public void confirmarReserva(Producto producto) {

        List<Producto> productos = productoRepository.findByReserva_IdReservaAndAndEstado(producto.getReserva().
                getIdReserva(), "IN_PROCESS");
        if (productos.isEmpty()) {
            Reserva reserva = producto.getReserva();
            reserva.setEstado("CONFIRMED");
            kafkaProducer.sendNotificacionesMessage(construirMensajeConfirmacion(reserva,"Le informamos que su reserva esta confirmada"));
            reservaRepository.save(reserva);
        }
    }

    public String construirMensajeConfirmacion(Reserva reserva, String mensaje) {
        return JsonConverter.toJSON(NotificationObject.builder().
                email(reserva.getUsuario().getEmail()).
                nombreUsuario(reserva.getUsuario().getFirstName() + reserva.getUsuario().getLastName()).
                valor((int) reserva.getValor()).
                referencia(Integer.valueOf(reserva.getIdReserva())).
                mensaje(mensaje).build());
    }



}
