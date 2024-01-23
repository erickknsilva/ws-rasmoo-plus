package com.client.wsRasmooplus.dto.token;

import lombok.Builder;

public record TokenDto(
        String token,
        String type
) {

    @Builder(toBuilder = true)
    public TokenDto {
    }
}
