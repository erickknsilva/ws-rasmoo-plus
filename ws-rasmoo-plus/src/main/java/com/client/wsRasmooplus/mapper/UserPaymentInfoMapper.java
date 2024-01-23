package com.client.wsRasmooplus.mapper;

import com.client.wsRasmooplus.dto.UserPaymentInfoDto;
import com.client.wsRasmooplus.model.User;
import com.client.wsRasmooplus.model.UserPaymentInfo;

public record UserPaymentInfoMapper() {

    public static UserPaymentInfo fromDtoToEntity(UserPaymentInfoDto dto, User user) {
        return UserPaymentInfo.builder()
                .id(dto.userId())
                .cardNumber(dto.cardNumber())
                .cardExpirationMonth(dto.cardExpirationMonth())
                .cardExpirationYear(dto.cardExpirationYear())
                .cardSecurityCode(dto.cardSecurityCode())
                .instalments(dto.instalments())
                .price(dto.price())
                .dtPayment(dto.dtPayment())
                .usersId(user)
                .build();

    }
}
