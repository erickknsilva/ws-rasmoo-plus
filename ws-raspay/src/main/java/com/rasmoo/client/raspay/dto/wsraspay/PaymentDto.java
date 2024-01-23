package com.rasmoo.client.raspay.dto.wsraspay;

import lombok.Builder;

@Builder(toBuilder = true)
public record PaymentDto(

        CreditCardDto creditCard,
        String customerId,
        String orderId
) {
}
