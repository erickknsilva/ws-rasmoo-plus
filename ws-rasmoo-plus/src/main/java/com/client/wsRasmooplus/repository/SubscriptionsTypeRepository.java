package com.client.wsRasmooplus.repository;

import com.client.wsRasmooplus.model.SubscriptionsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionsTypeRepository
        extends JpaRepository<SubscriptionsType, Long> {
    Optional<SubscriptionsType> findByProductKey(String productKey);
}
