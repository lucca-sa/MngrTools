package com.restapi.usermanagement.adapter.input.department.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.usermanagement.adapter.input.department.dto.Department;
import com.restapi.usermanagement.adapter.input.department.swagger.FindDepartmentByIdSwagger;
import com.restapi.usermanagement.adapter.mapper.DepartmentMapper;
import com.restapi.usermanagement.port.department.input.FindDepartmentByIdUseCase;

@RestController
@RequestMapping(value = "/api/department", produces = { "application/json" })
public class FindDepartmentByIdController implements FindDepartmentByIdSwagger {
    private final FindDepartmentByIdUseCase useCase;
    private final DepartmentMapper mapper;

    public FindDepartmentByIdController(FindDepartmentByIdUseCase useCase, DepartmentMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/{departmentId}")
    public Department getDepartmentInfo(@PathVariable Long departmentId) {
        return mapper.toResponse(useCase.findDepartmentInfo(departmentId));
    }
}
