package com.restapi.usermanagement.adapter.input.user.swagger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.restapi.usermanagement.adapter.exception.ErrorResponse;
import com.restapi.usermanagement.adapter.input.user.dto.UpdateUserRequest;
import com.restapi.usermanagement.adapter.input.user.dto.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Tag(name = "Users")
public interface UpdateUserSwagger {

    @Operation(summary = "Update a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserResponse.class))),
            @ApiResponse(description = "Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class))),
           
    })
    public UserResponse patchUser(@Valid @RequestBody UpdateUserRequest request,
            @PathVariable @Positive(message = "User ID must be valid.") Long userId);
}
