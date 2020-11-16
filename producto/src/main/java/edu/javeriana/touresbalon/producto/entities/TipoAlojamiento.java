package edu.javeriana.touresbalon.producto.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(name = "TIPO_ALOJAMIENTO")
@Data
public class TipoAlojamiento {

    @Id
    @Column(name = "ID_TIPO_ALOJAMIENTO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idTipoAlojamiento;

    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
}
