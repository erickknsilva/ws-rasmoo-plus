package com.client.wsRasmooplus.repository;

import com.client.wsRasmooplus.model.SubscriptionsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionsTypeRepository
        extends JpaRepository<SubscriptionsType, Long> {

}
