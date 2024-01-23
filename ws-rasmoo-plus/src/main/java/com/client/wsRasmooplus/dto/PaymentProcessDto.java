package com.client.wsRasmooplus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PaymentProcessDto(

        @NotBlank(message = "Insira o producKey")
        String processKey,

        BigDecimal discount,

        @JsonProperty("userPaymentInfo")
        @NotBlank
        @NotNull(message = "Insira os dados de pagamentos")
        UserPaymentInfoDto userPaymentInfoDto
) {


    @Builder(toBuilder = true)
    public PaymentProcessDto {
    }

}

//@Builder
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserPaymentProcessDto {
//
//    @NotBlank(message = "Insira o producKey")
//    private String processKey;
//
//    private BigDecimal discount;
//
//    @NotNull(message = "Insira os dados de pagamentos")
//    @JsonProperty("userPaymentInfo")
//    private UserPaymentInfoDto userPaymentInfoDto;
//
//}
