package com.client.wsRasmooplus.filter;

import com.client.wsRasmooplus.Exception.NotFoundException;
import com.client.wsRasmooplus.model.UserCredentials;
import com.client.wsRasmooplus.repository.UserCredentialsRepository;
import com.client.wsRasmooplus.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserCredentialsRepository userDetailsRepository;

    public AuthenticationFilter(TokenService tokenService, UserCredentialsRepository userDetailsRepository) {
        this.tokenService = tokenService;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getBearerToken(request);

        if (tokenService.isValid(token)) {
            authByToken(token);
        }

        filterChain.doFilter(request, response);

    }

    private void authByToken(String token) {

        var userOpt = userDetailsRepository.findById(tokenService.getUserId(token));

        if (userOpt.isEmpty()) {
            throw new NotFoundException("Usuario não encontrado.");
        }

        authenticateUser(userOpt);

    }

    private void authenticateUser(Optional<UserCredentials> userOpt) {
        UserCredentials userCredentials = userOpt.get();

        UsernamePasswordAuthenticationToken userAuth =
                new UsernamePasswordAuthenticationToken
                        (userCredentials, null, userCredentials.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userAuth);
    }

    private String getBearerToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (Objects.isNull(token) || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7, token.length());
    }


}