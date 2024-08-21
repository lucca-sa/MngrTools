package com.restapi.usermanagement.adapter.input.user.swagger;

import org.springframework.web.bind.annotation.PathVariable;

import com.restapi.usermanagement.adapter.input.user.dto.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users")
public interface FindUserByIdSwagger {
    @Operation(summary = "Find an User by ID")
    public UserResponse getUserInfo(@PathVariable Long userId);
}
