package com.client.wsRasmooplus.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String getToken(Authentication authentication);

    Boolean isValid(String token);

    Long getUserId(String token);
}
