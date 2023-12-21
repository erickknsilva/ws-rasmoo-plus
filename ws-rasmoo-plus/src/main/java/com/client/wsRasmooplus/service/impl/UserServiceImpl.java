package com.client.wsRasmooplus.service.impl;

import com.client.wsRasmooplus.Exception.AlReadyException;
import com.client.wsRasmooplus.Exception.NotFoundException;
import com.client.wsRasmooplus.dtos.UserDto;
import com.client.wsRasmooplus.mapper.UserMapper;
import com.client.wsRasmooplus.model.User;
import com.client.wsRasmooplus.model.UserType;
import com.client.wsRasmooplus.repository.UserRepository;
import com.client.wsRasmooplus.repository.UserTypeRepository;
import com.client.wsRasmooplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserTypeRepository userTypeRepository;
    private final UserRepository userRepository;

    public UserServiceImpl(UserTypeRepository userTypeRepository, UserRepository userRepository) {
        this.userTypeRepository = userTypeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserDto dto) {

        if (Objects.nonNull(dto.getId())) {
            throw new AlReadyException("O Id deve ser nulo");
        }

        var userTypeId = userTypeRepository.findById(dto.getUserTypeId());

        if (userTypeId.isEmpty()) {
            throw new NotFoundException("UserTypeId não encontrado.");
        }

        UserType userType = userTypeId.get();

        User user = UserMapper.fromDtoToEntity(dto, userType, null);

        return userRepository.save(user);
    }
}
