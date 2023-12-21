package com.client.wsRasmooplus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "subscriptions_type")
public class SubscriptionsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriptions_type_id")
    private Long id;

    private String name;

    private Long accessMonths;

    private BigDecimal price;

    @NotBlank(message = "Insira a chave do produto")
    @Size(max = 255)
    @Column(unique = true)
    private String productKey;


}
