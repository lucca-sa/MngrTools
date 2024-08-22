package com.restapi.usermanagement.adapter.input.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.usermanagement.adapter.input.user.swagger.DeleteUserSwagger;
import com.restapi.usermanagement.port.user.input.DeleteUserUseCase;

@RestController
@RequestMapping(value = "/api/user/{userId}", produces = { "application/json" })
public class DeleteUserController implements DeleteUserSwagger {
    private final DeleteUserUseCase useCase;

    public DeleteUserController(DeleteUserUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        useCase.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
