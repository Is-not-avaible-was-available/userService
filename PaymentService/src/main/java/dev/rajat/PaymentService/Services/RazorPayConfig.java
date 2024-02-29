package dev.rajat.PaymentService.Services;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {
    @Value("${razorpay.api.key}")
    private String key;
    @Value("${razorpay.api.secret}")
    private String secret;


    public RazorpayClient getClient() {
        try {
            return new RazorpayClient(key, secret);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }

}
