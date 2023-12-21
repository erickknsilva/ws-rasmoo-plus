package com.client.wsRasmooplus.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {

    private Long id;

    @NotBlank(message = "Insira o nome")
    @Size(min = 6, message = "O valor mínimo deve ser {min} caracteres.")
    private String name;

    @Email(message = "Email inválido, tente algo do tipo exemplo@gmail.com")
    private String email;

    @Size(min = 11, message = "O campo deve ter {min} dígitos")
    private String phone;

    @CPF(message = "CPF inválido.")
    private String cpf;

    @Builder.Default
    private LocalDate dtSubscription = LocalDate.now();

    @Builder.Default
    private LocalDate dtExpiration = LocalDate.now();

    @NotNull(message = "Insira a identificação do UserType")
    private Long userTypeId;

    private Long subscriptionsTypeId;

}
