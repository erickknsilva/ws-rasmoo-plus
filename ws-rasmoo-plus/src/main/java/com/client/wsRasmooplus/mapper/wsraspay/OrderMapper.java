package com.client.wsRasmooplus.mapper.wsraspay;

import com.client.wsRasmooplus.dto.PaymentProcessDto;
import com.client.wsRasmooplus.dto.wsraspay.OrderDto;

import java.math.BigDecimal;

public record OrderMapper() {

    public static OrderDto build(String customerId,
                                 PaymentProcessDto dto) {

        return OrderDto.builder()
                .customerId(customerId)
                .productAcronym(dto.processKey())
                .discount(dto.discount())
                .build();

    }
}
