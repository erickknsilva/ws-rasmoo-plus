package com.client.wsRasmooplus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user_payment_info")
public class UserPaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_payment_info_id")
    private Long id;

    private String cardNumber;

    private long cardExpirationMonth;

    private long cardExpirationYear;

    private String cardSecurityCode;

    private double price;

    private int instalments;

    private LocalDate dtPayment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User usersId;

}
