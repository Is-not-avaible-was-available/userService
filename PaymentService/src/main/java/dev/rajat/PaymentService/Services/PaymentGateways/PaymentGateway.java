package dev.rajat.PaymentService.Services.PaymentGateways;

import com.razorpay.PaymentLink;

public interface PaymentGateway {
    public String generateLink();
}
