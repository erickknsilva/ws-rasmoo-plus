package com.client.wsRasmooplus.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public record ErrorResponseDto(
        String message,
        HttpStatus httpStatus,
        Integer statusCode
) {

    @Builder(toBuilder = true)
    public ErrorResponseDto {
    }

}
