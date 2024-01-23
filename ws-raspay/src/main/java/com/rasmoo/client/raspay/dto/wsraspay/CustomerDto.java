package com.rasmoo.client.raspay.dto.wsraspay;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder(toBuilder = true)
public record CustomerDto(String id,
                          String cpf,
                          String email,
                          String firstName,
                          String lastName) {

}
