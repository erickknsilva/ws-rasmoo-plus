package com.client.wsRasmooplus.dto;


import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionsTypeDto {

    private Long id;

    @NotNull(message = "O campo name não pode ser nulo.")
    @NotBlank(message = "O campo name não pode ser vazio.")
    @Size(min = 5, max = 30, message = "O campo deve estar entre {min} e {max} caractere.")
    private String name;

    //    @Digits(integer = 2, fraction = 0, message = "O campo 'accessMonths' deve ter até 2 dígitos inteiros.")
    @Max(value = 12L, message = "O campo 'accessMonths' não pode ser maior que {value}.")
    private Long accessMonths;

    @NotNull(message = "O preço não pode ser nulo.")
    @Positive
    private BigDecimal price;

    @NotBlank(message = "O campo Product Key não pode ser nulo.")
    @Size(min = 5, max = 15, message = "O campo deve estar entre {min} e {max} caractere.")
    private String productKey;
}
