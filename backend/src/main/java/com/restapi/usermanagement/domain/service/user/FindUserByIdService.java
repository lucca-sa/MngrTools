package com.restapi.usermanagement.domain.service.user;

import org.springframework.stereotype.Service;

import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.port.user.input.FindUserByIdUseCase;
import com.restapi.usermanagement.port.user.output.FindUserByIdPort;

@Service
public class FindUserByIdService implements FindUserByIdUseCase {
    private final FindUserByIdPort port;

    public FindUserByIdService(FindUserByIdPort port) {
        this.port = port;
    }

    @Override
    public UserModel findUserInfo(Long userId) {
        return port.findUserInfo(userId);
    }
}
