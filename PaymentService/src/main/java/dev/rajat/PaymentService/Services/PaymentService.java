package dev.rajat.PaymentService.Services;

import com.razorpay.PaymentLink;
import dev.rajat.PaymentService.DTos.InitiatePaymentDto;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    public PaymentService(PaymentGatewayChooserStrategy paymentGatewayChooserStrategy){
        this.paymentGatewayChooserStrategy = paymentGatewayChooserStrategy;
    }

    public String initiatePayment(){
        return paymentGatewayChooserStrategy.getBestPaymentGateway().generateLink();
    }
}
