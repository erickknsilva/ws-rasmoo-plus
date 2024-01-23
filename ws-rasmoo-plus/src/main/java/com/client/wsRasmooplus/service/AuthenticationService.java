package com.client.wsRasmooplus.service;

import com.client.wsRasmooplus.dto.LoginRequest;
import com.client.wsRasmooplus.dto.token.TokenDto;

public interface AuthenticationService {

    TokenDto auth(LoginRequest request);

}
