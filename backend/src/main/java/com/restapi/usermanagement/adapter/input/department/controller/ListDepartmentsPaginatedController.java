package com.restapi.usermanagement.adapter.input.department.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.usermanagement.adapter.input.department.swagger.ListDepartmentsPaginatedSwagger;
import com.restapi.usermanagement.adapter.input.generic.PaginationResponseDTO;
import com.restapi.usermanagement.adapter.mapper.PaginationMapper;
import com.restapi.usermanagement.port.department.input.ListDepartmentsPaginatedUseCase;

@RestController
@RequestMapping(value = "/api/department", produces = { "application/json" })
public class ListDepartmentsPaginatedController implements ListDepartmentsPaginatedSwagger {
    private final ListDepartmentsPaginatedUseCase useCase;
    private final PaginationMapper mapper;

    public ListDepartmentsPaginatedController(ListDepartmentsPaginatedUseCase useCase, PaginationMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    @GetMapping()
    public PaginationResponseDTO getDepartmentList(@PageableDefault(page = 0, size = 10) Pageable page,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String departmentName) {
        return mapper.toResponseDTO(useCase.findDepartmentList(page, departmentId, departmentName));
    }
}
