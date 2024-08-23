package com.restapi.usermanagement.adapter.input.user.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.usermanagement.adapter.input.user.dto.UpdateUserRequest;
import com.restapi.usermanagement.adapter.input.user.dto.User;
import com.restapi.usermanagement.adapter.input.user.swagger.UpdateUserSwagger;
import com.restapi.usermanagement.adapter.mapper.UserMapper;
import com.restapi.usermanagement.port.user.input.UpdateUserUseCase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/api/user", produces = { "application/json" })
public class UpdateUserController implements UpdateUserSwagger {
    private final UpdateUserUseCase useCase;
    private final UserMapper mapper;

    public UpdateUserController(UpdateUserUseCase useCase, UserMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    @PatchMapping("/{userId}")
    public User patchUser(@Valid @RequestBody UpdateUserRequest request,
            @PathVariable @Positive(message = "User ID must be valid.") Long userId) {
        return mapper.toResponse(useCase.update(userId, mapper.toModel(request)));
    }
}
