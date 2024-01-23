package com.client.wsRasmooplus.service;

import com.client.wsRasmooplus.dto.UserDto;
import com.client.wsRasmooplus.model.User;

public interface UserService {

    User create(UserDto dto);

}
