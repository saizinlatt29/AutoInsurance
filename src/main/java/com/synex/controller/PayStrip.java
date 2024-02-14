package com.synex.controller;

import com.stripe.exception.StripeException;
import com.synex.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PayStrip {
    @Autowired
    PaymentService paymentService;
    @RequestMapping(value = "/checkout/{amount}")
    public Map<String,String> checkout(@PathVariable long amount){
        try {
            Map<String,String>  map = new HashMap<>();
            map.put("sessionId",paymentService.createCheckoutSession(amount));
            return map;
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

}
