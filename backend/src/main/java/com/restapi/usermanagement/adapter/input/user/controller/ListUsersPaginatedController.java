package com.restapi.usermanagement.adapter.input.user.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.usermanagement.adapter.input.generic.PaginationResponseDTO;
import com.restapi.usermanagement.adapter.input.user.swagger.ListUsersPaginatedSwagger;
import com.restapi.usermanagement.adapter.mapper.PaginationMapper;
import com.restapi.usermanagement.port.user.input.ListUsersPaginatedUseCase;

@RestController
@RequestMapping(value = "/api/user", produces = { "application/json" })
public class ListUsersPaginatedController implements ListUsersPaginatedSwagger {
    private final ListUsersPaginatedUseCase useCase;
    private final PaginationMapper mapper;

    public ListUsersPaginatedController(ListUsersPaginatedUseCase useCase, PaginationMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    @GetMapping()
    public PaginationResponseDTO getUserList(@PageableDefault(page = 0, size = 10) Pageable page,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String departmentName) {
        return mapper.toResponseDTO(useCase.findUserList(page, userId, userName, departmentName));
    }
}
