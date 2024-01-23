package com.client.wsRasmooplus.service.impl.tokenAnduser;

import com.client.wsRasmooplus.Exception.NotFoundException;
import com.client.wsRasmooplus.model.UserCredentials;
import com.client.wsRasmooplus.repository.UserCredentialsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserCredentialsRepository userDetailsRepository;

    public UserDetailsServiceImpl(UserCredentialsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserCredentials> usernamelOpt = userDetailsRepository.findByUsername(username);

        if (usernamelOpt.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        return usernamelOpt.get();

    }


}
