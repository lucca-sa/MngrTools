package com.restapi.usermanagement.adapter.output;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.restapi.usermanagement.adapter.mapper.UserMapper;
import com.restapi.usermanagement.adapter.output.database.repository.UserRepository;
import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.port.user.output.FindUserByIdPort;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserPersistence implements FindUserByIdPort {
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    @Override
    public UserModel findUserInfo(Long userId) {
        return userMapper.toModel(
                userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found.")));
    }
}
