package com.restapi.usermanagement.adapter.input.department.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.usermanagement.adapter.input.department.dto.CreateDepartmentRequest;
import com.restapi.usermanagement.adapter.input.department.dto.Department;
import com.restapi.usermanagement.adapter.input.department.swagger.CreateDepartmentSwagger;
import com.restapi.usermanagement.adapter.mapper.DepartmentMapper;
import com.restapi.usermanagement.port.department.input.CreateDepartmentUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/department", produces = { "application/json" })
public class CreateDepartmentController implements CreateDepartmentSwagger {
    private final CreateDepartmentUseCase useCase;
    private final DepartmentMapper mapper;

    public CreateDepartmentController(CreateDepartmentUseCase useCase, DepartmentMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    @PostMapping
    public Department postNewDepartment(@Valid @RequestBody CreateDepartmentRequest request) {
        return mapper.toResponse(useCase.create(mapper.toModel(request)));
    }
}
