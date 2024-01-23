package com.rasmoo.client.raspay.dto.wsraspay;

import lombok.Builder;

@Builder(toBuilder = true)
public record CreditCardDto(
        Long cvv,
        String documentNumber,
        Long installments,
        Long month,
        String number,
        Long year
) {

}
