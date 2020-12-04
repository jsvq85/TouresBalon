package edu.javeriana.touresbalon.reserva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
