package com.client.wsRasmooplus.mapper;

import com.client.wsRasmooplus.dto.SubscriptionsTypeDto;
import com.client.wsRasmooplus.model.SubscriptionsType;

public record SubscriptionTypeMapper() {

    public static SubscriptionsType fromDtoToEntity(SubscriptionsTypeDto dto) {
        return SubscriptionsType.builder()
                .id(dto.getId())
                .name(dto.getName())
                .accessMonths(dto.getAccessMonths())
                .price(dto.getPrice())
                .productKey(dto.getProductKey())
                .build();
    }

}
