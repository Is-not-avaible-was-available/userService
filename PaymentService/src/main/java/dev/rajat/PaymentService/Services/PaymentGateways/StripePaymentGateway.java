package dev.rajat.PaymentService.Services.PaymentGateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements PaymentGateway{
    @Value("${stripe.api.secret}")
    String secretKey;
    @Override
    public String generateLink() {
        Stripe.apiKey = secretKey;
        try{
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("inr")
                            .setUnitAmount(1000L)
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Test-Product").build()
                            )
                            .build();

            Price price = Price.create(params);

//            CustomerCreateParams paramsCustomer = CustomerCreateParams.builder()
//                    .setEmail("yadraj1803@gmail.com")
//                    .setName("Rajat Yadav").setAddress(
//                            CustomerCreateParams.Address.builder().
//                                    setCity("Mumbai").setState("Maharashtra")
//                                    .setCountry("India")
//                                    .setLine1("1/2 South Mumbai")
//                                    .setPostalCode("112211")
//                                    .build()
//                    )
//                    .build();
//            Customer customer =  Customer.create(paramsCustomer);

            PaymentLinkCreateParams paramsPaymentLink =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .build();
            PaymentLink paymentLink = PaymentLink.create(paramsPaymentLink);

            return paymentLink.getUrl();

        } catch (StripeException e){
            System.out.println(e);
        }
        return null;
    }
}
