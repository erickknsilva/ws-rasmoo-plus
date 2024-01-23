package com.client.wsRasmooplus.mapper.wsraspay;

import com.client.wsRasmooplus.dto.wsraspay.CustomerDto;
import com.client.wsRasmooplus.model.User;

public record CustomerMapper() {

    public static CustomerDto buildCustomerDto(User user) {

        var fullName = user.getName().split(" ");
        var firstName = fullName[0];
        var lastName = fullName.length > 1 ? fullName[fullName.length - 1] : "";

        return CustomerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(user.getEmail())
                .cpf(user.getCpf())
                .build();
    }
}
