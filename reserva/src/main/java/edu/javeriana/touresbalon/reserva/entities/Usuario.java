package edu.javeriana.touresbalon.reserva.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Data
public class Usuario {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Reserva> reservaList;

}
