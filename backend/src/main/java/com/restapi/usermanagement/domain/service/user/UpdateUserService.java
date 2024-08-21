package com.restapi.usermanagement.domain.service.user;

import org.springframework.stereotype.Service;

import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.domain.model.UserRequestModel;
import com.restapi.usermanagement.port.user.input.UpdateUserUseCase;
import com.restapi.usermanagement.port.user.output.UpdateUserPort;

@Service
public class UpdateUserService implements UpdateUserUseCase {
    private final UpdateUserPort port;

    public UpdateUserService(UpdateUserPort port) {
        this.port = port;
    }

    @Override
    public UserModel update(Long userId, UserRequestModel model) {
        return port.update(userId, model);
    }
}
