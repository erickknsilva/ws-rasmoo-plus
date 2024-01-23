package com.client.wsRasmooplus.mapper.wsraspay;

import com.client.wsRasmooplus.dto.wsraspay.CreditCardDto;
import com.client.wsRasmooplus.dto.wsraspay.PaymentDto;
import org.hibernate.validator.constraints.CreditCardNumber;

public record PaymentMapper() {

    public static PaymentDto buildPaymentDto(String customerId, String orderId, CreditCardDto cardNumber) {

        return PaymentDto.builder()
                .customerId(customerId)
                .orderId(orderId)
                .creditCard(cardNumber)
                .build();

    }

}
