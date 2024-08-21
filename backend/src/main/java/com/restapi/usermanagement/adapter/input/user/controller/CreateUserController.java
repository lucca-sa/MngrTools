package com.restapi.usermanagement.adapter.input.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.usermanagement.adapter.input.user.dto.CreateUserRequest;
import com.restapi.usermanagement.adapter.input.user.dto.UserResponse;
import com.restapi.usermanagement.adapter.input.user.swagger.CreateUserSwagger;
import com.restapi.usermanagement.adapter.mapper.UserMapper;
import com.restapi.usermanagement.port.user.input.CreateUserUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/user", produces = { "application/json" })
public class CreateUserController implements CreateUserSwagger {
    private final CreateUserUseCase useCase;
    private final UserMapper mapper;

    public CreateUserController(CreateUserUseCase useCase, UserMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    @PostMapping
    public UserResponse postNewUser(@Valid @RequestBody CreateUserRequest request) {
        return mapper.toResponse(useCase.create(mapper.requestToModel(request)));
    }
}
