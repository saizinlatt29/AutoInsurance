package com.synex.utils;

import com.synex.domain.InsurancePlan;
import com.synex.domain.Payment;
import com.synex.domain.StripeChargeDto;
import com.synex.service.InsurancePlanService;
import com.synex.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CommandLineRunner1 implements CommandLineRunner {
    @Autowired
    InsurancePlanService planService;

    @Autowired
    PaymentService paymentService;
    @Override
    public void run(String... args) throws Exception {
        Payment payment =  new Payment();
        payment.setExpireMonth("February");
        payment.setExpireYear("2027");
        payment.setCreditCardNumber("4242 4242 4242 4242");
        payment.setCvv(451);
       payment = paymentService.createCardToken(payment);
        StripeChargeDto dto= new StripeChargeDto();
        dto.setAmount(52.34);
        dto.setStripeToken(payment.getToken());
        dto.setUsername("sai");
        Map<String,Object> map = new HashMap<>();
        Object string = "1234567890";
        map.put("ID_TAG","12324");
        dto=paymentService.charge(dto);
        System.out.println(dto);


    }
}
