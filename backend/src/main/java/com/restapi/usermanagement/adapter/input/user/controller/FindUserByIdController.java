package com.restapi.usermanagement.adapter.input.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.usermanagement.adapter.input.user.dto.User;
import com.restapi.usermanagement.adapter.input.user.swagger.FindUserByIdSwagger;
import com.restapi.usermanagement.adapter.mapper.UserMapper;
import com.restapi.usermanagement.port.user.input.FindUserByIdUseCase;

@RestController
@RequestMapping(value = "/api/user", produces = { "application/json" })
public class FindUserByIdController implements FindUserByIdSwagger {
    private final FindUserByIdUseCase useCase;
    private final UserMapper mapper;

    public FindUserByIdController(FindUserByIdUseCase useCase, UserMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/{userId}")
    public User getUserInfo(@PathVariable Long userId) {
        return mapper.toResponse(useCase.findUserInfo(userId));
    }
}
