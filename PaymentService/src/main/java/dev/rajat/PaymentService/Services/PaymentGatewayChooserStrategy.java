package dev.rajat.PaymentService.Services;

import dev.rajat.PaymentService.Services.PaymentGateways.PaymentGateway;
import dev.rajat.PaymentService.Services.PaymentGateways.RazorPayPaymentGateway;
import dev.rajat.PaymentService.Services.PaymentGateways.StripePaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayChooserStrategy {
    private RazorPayPaymentGateway razorPayPaymentGateway;
    private StripePaymentGateway stripePaymentGateway;

    public PaymentGatewayChooserStrategy(RazorPayPaymentGateway razorPayPaymentGateway,
                                         StripePaymentGateway stripePaymentGateway){
        this.razorPayPaymentGateway = razorPayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway getBestPaymentGateway(){
        //logic to choose the best payment gateway
        return stripePaymentGateway;
    }


}
