package dev.rajat.PaymentService.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripeWebhook")
public class StripeWebHookController {

    @PostMapping
    public void stripeWebhook(){
        System.out.println("In Stripe WebHook");
    }
}
