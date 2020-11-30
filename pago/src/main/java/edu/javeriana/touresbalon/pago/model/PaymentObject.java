package edu.javeriana.touresbalon.pago.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentObject {
    private String cardType;
    private long cardNumber;
    private String cardDate;
    private int cvv;
    private int due;
    private long cardOwnerId;
    private String cardOwnerName;
}
