package dev.rajat.PaymentService.DTos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentDto {
    private int amount;
    private String currency;
    private String description;
}
