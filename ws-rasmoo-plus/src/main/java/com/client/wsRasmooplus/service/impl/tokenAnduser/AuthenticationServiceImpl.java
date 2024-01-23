package com.client.wsRasmooplus.service.impl.tokenAnduser;

import com.client.wsRasmooplus.Exception.BadRequestException;
import com.client.wsRasmooplus.dto.LoginRequest;
import com.client.wsRasmooplus.dto.token.TokenDto;
import com.client.wsRasmooplus.service.AuthenticationService;
import com.client.wsRasmooplus.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    @Override
    public TokenDto auth(LoginRequest request) {
        try {

            UsernamePasswordAuthenticationToken authenticationRequest = new
                    UsernamePasswordAuthenticationToken(request.username(), request.password());

            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);

            String token = tokenService.getToken(authenticationResponse);

            return TokenDto.builder().token(token).type("Bearer").build();

        } catch (Exception e) {
            throw new BadRequestException("Erro ao formatar token - " + e.getMessage());
        }
    }
}
