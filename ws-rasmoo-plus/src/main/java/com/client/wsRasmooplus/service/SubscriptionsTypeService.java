package com.client.wsRasmooplus.service;

import com.client.wsRasmooplus.dto.SubscriptionsTypeDto;
import com.client.wsRasmooplus.model.SubscriptionsType;

import java.util.List;

public interface SubscriptionsTypeService {

    List<SubscriptionsType> findAll();

    SubscriptionsType findById(Long id);

    SubscriptionsType create(SubscriptionsTypeDto subscriptionsType);

    SubscriptionsType update(Long id, SubscriptionsTypeDto dto);

    void delete(Long id);
}
