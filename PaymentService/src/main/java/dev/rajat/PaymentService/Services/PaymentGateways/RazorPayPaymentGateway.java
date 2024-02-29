package dev.rajat.PaymentService.Services.PaymentGateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dev.rajat.PaymentService.Services.RazorPayConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class RazorPayPaymentGateway implements PaymentGateway {

    private final RazorPayConfig razorPayConfig;
    @Autowired
    public RazorPayPaymentGateway(RazorPayConfig razorPayConfig) {
        this.razorPayConfig = razorPayConfig;
    }
    @Override
    public String generateLink() {

        RazorpayClient razorpay = razorPayConfig.getClient();
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", 1000);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", true);
            paymentLinkRequest.put("first_min_partial_amount", 100);
            paymentLinkRequest.put("expire_by", 1709389323);
            paymentLinkRequest.put("reference_id", "TS1980");
            paymentLinkRequest.put("description", "product description for product(#24567)");
            JSONObject customer = new JSONObject();
            customer.put("contact", "+917987643689");
            customer.put("name", "Rajat Yadav");
            customer.put("email", "yadraj1803@gmail.com");
            paymentLinkRequest.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("reminder_enable", true);
            JSONObject notes = new JSONObject();
            notes.put("policy_name", "Jeevan Bima");
            paymentLinkRequest.put("notes", notes);
            paymentLinkRequest.put("callback_url", "https://www.google.com/");
            paymentLinkRequest.put("callback_method", "get");
            PaymentLink payment;
            try {
                payment = razorpay.paymentLink.create(paymentLinkRequest);
            } catch (RazorpayException e) {
                throw new RuntimeException(e);
            }
            return payment.toString();
        }
    }