
package com.client.wsRasmooplus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @NotBlank(message = "Insira o nome")
    @Size(max = 255)
    private String name;

    @Email(message = "Insira o email")
    @Size(max = 255)
    @Column(unique = true)
    private String email;

    @Size(min = 11, message = "O campo deve ter {min} dígitos")
    private String phone;

    @NotBlank(message = "Insira o cpf")
    private String cpf;

    @Builder.Default
    private LocalDate dtSubscription = LocalDate.now();

    @Builder.Default
    private LocalDate dtExpiration = LocalDate.now();

    @JoinColumn(name = "user_type_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserType userTypeId;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriptions_type_id")
    private SubscriptionsType subscriptionsTypeId;

}
