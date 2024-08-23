package com.restapi.usermanagement.adapter.input.user.swagger;

import org.springframework.web.bind.annotation.PathVariable;

import com.restapi.usermanagement.adapter.exception.ErrorResponse;
import com.restapi.usermanagement.adapter.input.user.dto.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users")
public interface FindUserByIdSwagger {
    @Operation(summary = "Find a User by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = User.class))),
            @ApiResponse(description = "Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class))),
           
    })
    public User getUserInfo(@PathVariable Long userId);
}
