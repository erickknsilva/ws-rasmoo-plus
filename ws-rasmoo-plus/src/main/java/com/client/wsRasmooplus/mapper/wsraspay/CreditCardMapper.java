package com.client.wsRasmooplus.mapper.wsraspay;

import com.client.wsRasmooplus.dto.PaymentProcessDto;
import com.client.wsRasmooplus.dto.UserPaymentInfoDto;
import com.client.wsRasmooplus.dto.wsraspay.CreditCardDto;
import javax.validation.Valid;

public record CreditCardMapper() {

    public static CreditCardDto buildCardNumbertDto(@Valid UserPaymentInfoDto dto, String documentNumber) {
        return CreditCardDto.builder()
                .documentNumber(documentNumber)
                .number(dto.cardNumber())
                .month(dto.cardExpirationMonth())
                .year(dto.cardExpirationYear())
                .cvv(Long.valueOf(dto.cardSecurityCode()))
                .installments(dto.instalments())
                .build();
    }
}
