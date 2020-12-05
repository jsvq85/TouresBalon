package edu.javeriana.touresbalon.reserva.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PAYMENT")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Data
public class Payment {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "cardType")
    private String cardType;
    @Column(name = "idConvenio")
    private Integer idConvenio;
    @Column(name = "cardNumber")
    private long cardNumber;
    @Column(name = "cardDate")
    private String cardDate;
    @Column(name = "cvv")
    private int cvv;
    @Column(name = "due")
    private int due;
    @Column(name = "cardOwnerId")
    private long cardOwnerId;
    @Column(name = "CARD_OWNER_NAME")
    private String cardOwnerName;
    @OneToOne(mappedBy = "payment")
    private Usuario usuario;

}
