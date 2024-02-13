package com.synex.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.*;
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
        Stripe.apiKey=stripKey;
    }

//    public InsurancePlan testing(InsurancePlan plan) throws StripeException {
//        Stripe.apiKey=stripKey;
////        Map<String, Object> params = new HashMap<>();
////        params.put("name",plan.getPayment().getNameOnCard());
////        params.put("email",plan.getUserEmail());
//        Stripe.apiKey = "sk_test_51Oj4LIDSrVufG74c4LVLKIyZku9qcQ5sLOqVF5MXfaVAWIdes5fmuUfvlx7qrn4SyR6JaDtSsToremUYfvBgk3Cf00CeKbhKgs";
//
//
//        CustomerCreateParams customerCreateParams =CustomerCreateParams.builder()
//                .setEmail(plan.getUserEmail())
//                .setName(plan.getPayment().getNameOnCard())
//                .build();
//        Customer customer=Customer.create(customerCreateParams);
//
////        Map<String, Object> card = new HashMap<>();
////        card.put("number",plan.getPayment().getCreditCardNumber());
////        card.put("exp_month",plan.getPayment().getExpireMonth());
////        card.put("exp_year",plan.getPayment().getExpireYear());
////        card.put("cvc",plan.getPayment().getCvv());
//        String postalCode = ""+plan.getAddress().getPostalCode();
//
//        //Create payment method using card information
//        PaymentMethodCreateParams.CardDetails cardDetails = PaymentMethodCreateParams.CardDetails.builder()
//                .setNumber("4242424242424242")
//                .setExpMonth(monthToLong(plan.getPayment().getExpireMonth()) )
//                .setExpYear(Long.parseLong(plan.getPayment().getExpireYear()))
//                .setCvc(Integer.toString(plan.getPayment().getCvv()))
//                .build();
//
//
//        PaymentMethodCreateParams.BillingDetails billingDetails = PaymentMethodCreateParams.BillingDetails.builder()
//                .setName(plan.getPayment().getNameOnCard())
//                .setEmail(plan.getUserEmail())
//                .setAddress(PaymentMethodCreateParams.BillingDetails.Address.builder()
//                        .setLine1(plan.getAddress().getLine1())
//                        .setLine1(plan.getAddress().getLine2())
//                        .setCity(plan.getAddress().getCity())
//                        .setState(plan.getAddress().getState())
//                        .setCountry("US")
//                        .setPostalCode(postalCode)
//                        .build())
//                .build();
//
//        //Create payment method
//        PaymentMethodCreateParams paymentMethodParams = PaymentMethodCreateParams.builder()
//                .setType(PaymentMethodCreateParams.Type.CARD)
//                .setCard(cardDetails)
//                .setBillingDetails(billingDetails)
//                .build();
//
//        //Perform any necessary payment processing or integration with a payment gateway
//        PaymentIntentCreateParams.Builder paramsBuilder = new PaymentIntentCreateParams.Builder()
//                .setAmount((long) plan.getTotal())
//                .addPaymentMethodType("card")
//                .setCurrency("usd")
//                .setDescription("Auto insurance payment")
//                .setCustomer(customer.getId())
//                .setReceiptEmail(plan.getUserEmail())
//                .setReturnUrl("http://localhost:8080/insurance")
//                .setConfirm(true)
//                // Add address check
//                .setUseStripeSdk(true)
//                .setSetupFutureUsage(PaymentIntentCreateParams.SetupFutureUsage.OFF_SESSION)
//                .addPaymentMethodType("card_present");
//             PaymentIntentCreateParams params1 = paramsBuilder.build();
//            PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);
//            PaymentIntent paymentIntent = PaymentIntent.create(params1);
//            //retrieve the charges associated with the payment intent
//            ChargeListParams chargeListParams = ChargeListParams.builder()
//                    .setPaymentIntent(paymentIntent.getId())
//                    .build();
//
//        //The Charge object represents a single attempt to move money into your Stripe account
//        List<Charge> charges = Charge.list(chargeListParams).getData();
//
//        String paymentIntentId = paymentIntent.getId();
//
//        Charge associatedCharge = null;
//
//        for (Charge charge : charges) {
//            if (charge.getPaymentIntent() != null && charge.getPaymentIntent().equals(paymentIntentId)) {
//                associatedCharge = charge;
//                break;
//            }
//        }
//        //save in the database
//        boolean paymentSuccessful = paymentIntent.getStatus().equals("succeeded");
//        return plan;
//    }

    public long monthToLong(String  month){
        return switch (month){
            case "January" -> 1;
            case "February" -> 2;
            case "March" -> 3;
            case "April" -> 4;
            case "May" -> 5;
            case "June" -> 6;
            case "July" -> 7;
            case "August" -> 8;
            case "September" -> 9;
            case "October" -> 10;
            case "November" -> 11;
            case "December" -> 12;
            default -> 00;
        };
    }

    public Payment createCardToken(Payment model) {
        Stripe.apiKey=stripKey;

        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", model.getCreditCardNumber());
            card.put("exp_month", monthToLong(model.getExpireMonth()));
            card.put("exp_year", Integer.parseInt(model.getExpireYear()));
            card.put("cvc", model.getCvv());
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
            if (token != null && token.getId() != null) {
                model.setSuccess(true);
                model.setToken(token.getId());
            }
            return model;
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    public StripeChargeDto charge(StripeChargeDto chargeRequest) {
        Stripe.apiKey=stripKey;

        try {
            chargeRequest.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "USD");
            chargeParams.put("description", "Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
            chargeParams.put("source", chargeRequest.getStripeToken());
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());
            chargeParams.put("metadata", metaData);
            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);

            }
            return chargeRequest;
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
