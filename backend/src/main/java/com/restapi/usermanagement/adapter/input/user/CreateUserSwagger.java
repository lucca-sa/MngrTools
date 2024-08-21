package com.restapi.usermanagement.adapter.input.user;

import org.springframework.web.bind.annotation.RequestBody;

import com.restapi.usermanagement.adapter.input.user.dto.CreateUserRequest;
import com.restapi.usermanagement.adapter.input.user.dto.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Users")
public interface CreateUserSwagger {
    @Operation(summary = "Create an User")
    public UserResponse postNewUser(@Valid @RequestBody CreateUserRequest request);
}
