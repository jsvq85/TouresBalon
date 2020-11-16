package edu.javeriana.touresbalon.producto.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "TIPO_HABITACION")
@Data
public class TipoHabitacion {

    @Id
    @Column(name = "ID_TIPO_HABITACION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idTipoHabitacion;

    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
}
