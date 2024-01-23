package com.rasmoo.client.raspay.dto.wsraspay;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder(toBuilder = true)
public record OrderDto(
        String id,
        String customerId,
        Long discount,
        String productAcronym,
        String lastName) {

}
