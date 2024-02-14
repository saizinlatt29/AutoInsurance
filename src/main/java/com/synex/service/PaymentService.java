package com.synex.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.param.*;
import com.stripe.param.checkout.SessionCreateParams;
import com.synex.domain.InsurancePlan;
import com.synex.domain.Payment;
import com.synex.domain.StripeChargeDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PaymentService {
    @Value("${stripe.apikey}")
    private String stripKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripKey;
    }
    public String createCheckoutSession(long amount) throws StripeException {
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setUnitAmount(amount)
                                .setCurrency("usd")
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Insurance Payment")
                                        .build())
                                .build())
                        .setQuantity(1L)
                        .build())
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://dashboard.stripe.com/test/payments")
                .setCancelUrl("https://dashboard.stripe.com/test/payments")
                .build();

        Session session = Session.create(params);
        return session.getId();
    }

}
