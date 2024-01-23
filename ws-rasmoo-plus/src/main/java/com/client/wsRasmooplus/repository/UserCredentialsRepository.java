package com.client.wsRasmooplus.repository;

import com.client.wsRasmooplus.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    Optional<UserCredentials> findByUsername(String username);


}
