package com.client.wsRasmooplus.dto;

import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

public record UserPaymentInfoDto(

        Long userPaymentInfoId,

        @NotBlank(message = "Insira o numero do cartão")
        @Length(min = 16, max = 16, message = "Deve conter 16 numeros")
        String cardNumber,

        @NotNull(message = "Insira o mes de expiração do cartão")
        @Min(1)
        @Max(12)
        Long cardExpirationMonth,

        @NotNull(message = "Insira o ano de expiração do cartão")
        @Positive
        @Min(2023)
        Long cardExpirationYear,

        @NotNull(message = "Insira o codigo de segurança")
        @Size(min = 3, max = 3, message = "O codigo deve conter 3(três) numeros")
        String cardSecurityCode,

        @NotNull
        @Positive
        Double price,

        Integer instalments,

        LocalDate dtPayment,

        @NotBlank(message = "UserId deve ser informado")
        Long userId

) {

    @Builder(toBuilder = true)
    public UserPaymentInfoDto {
    }

}

//
//@Builder
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserPaymentInfoDto {
//
//
//    private Long userPaymentInfoId;
//
//    @Size(min = 16, max = 16, message = "Deve conter 16 numeros")
//    private String cardNumber;
//
//    @Min(1)
//    @Max(12)
//    private int cardExpirationMonth;
//
//
//    private int cardExpirationYear;
//
//    @Size(min = 3, max = 3, message = "O codigo deve conter 3(três) numeros")
//    private String cardSecurityCode;
//
//    @NotNull
//    @Positive
//    private double price;
//
//    private int instalments;
//
//    private LocalDate dtPayment = LocalDate.now();
//
//    @NotBlank(message = "UserId deve ser informado")
//    private Long userId;
//
//}
