package com.restapi.usermanagement.domain.service.user;

import org.springframework.stereotype.Service;

import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.domain.model.UserRequestModel;
import com.restapi.usermanagement.port.user.input.CreateUserUseCase;
import com.restapi.usermanagement.port.user.output.CreateUserPort;

@Service
public class CreateUserService implements CreateUserUseCase {
    private final CreateUserPort port;

    public CreateUserService(CreateUserPort port) {
        this.port = port;
    }

    @Override
    public UserModel create(UserRequestModel model) {
        return port.create(model);
    }
}
