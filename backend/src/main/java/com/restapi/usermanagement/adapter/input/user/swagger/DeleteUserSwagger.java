package com.restapi.usermanagement.adapter.input.user.swagger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users")
public interface DeleteUserSwagger {
    @Operation(summary = "Delete a User by ID")
    ResponseEntity<Void> deleteUser(@PathVariable Long userId);
}
