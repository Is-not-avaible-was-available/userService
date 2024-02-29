package dev.rajat.PaymentService.Controllers;

import com.razorpay.PaymentLink;
import dev.rajat.PaymentService.DTos.InitiatePaymentDto;
import dev.rajat.PaymentService.Services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping()
    public String initiatePayment(){
        return paymentService.initiatePayment();
    }
}
