package edu.javeriana.touresbalon.reserva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String cardType;
    private Integer idConvenio;
    private long cardNumber;
    private String cardDate;
    private int cvv;
    private int due;
    private long cardOwnerId;
    private String cardOwnerName;
}
