package com.client.wsRasmooplus.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder(toBuilder = true)
public record LoginRequest(

        @NotBlank(message = "Campo obrigatorio")
        String username,
        @NotBlank(message = "Campo obrigatorio")
        String password) {

}