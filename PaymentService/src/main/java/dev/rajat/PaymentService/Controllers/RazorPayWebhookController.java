package dev.rajat.PaymentService.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/razorpaywebhook")
public class RazorPayWebhookController {

    @PostMapping
    public void RazorPayWebhook(){
        System.out.println("RazorPayWebHook");
    }
}
