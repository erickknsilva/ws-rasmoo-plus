package com.client.wsRasmooplus.dto.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private String id;

    @NotBlank(message = "customerId é obrigatório")
    private String customerId;

    @NotBlank(message = "productAcronym é obrigatório")
    private String productAcronym;

    private BigDecimal discount;
}
