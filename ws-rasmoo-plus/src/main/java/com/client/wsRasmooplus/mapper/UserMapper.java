package com.client.wsRasmooplus.mapper;

import com.client.wsRasmooplus.dto.UserDto;
import com.client.wsRasmooplus.model.SubscriptionsType;
import com.client.wsRasmooplus.model.User;
import com.client.wsRasmooplus.model.UserType;

public class UserMapper {

    public static User fromDtoToEntity(UserDto dto, UserType type, SubscriptionsType subscriptionsType) {
        return User.builder()
                .usersId(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .phone(dto.getPhone())
                .dtSubscription(dto.getDtSubscription())
                .dtExpiration(dto.getDtExpiration())
                .userTypeId(type)
                .subscriptionsTypeId(subscriptionsType)
                .build();

    }
}
