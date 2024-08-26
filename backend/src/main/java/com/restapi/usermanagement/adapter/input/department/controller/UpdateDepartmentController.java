package com.restapi.usermanagement.adapter.input.department.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.usermanagement.adapter.input.department.dto.Department;
import com.restapi.usermanagement.adapter.input.department.dto.DepartmentRequest;
import com.restapi.usermanagement.adapter.input.department.swagger.UpdateDepartmentSwagger;
import com.restapi.usermanagement.adapter.mapper.DepartmentMapper;
import com.restapi.usermanagement.port.department.input.UpdateDepartmentUseCase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/api/department", produces = { "application/json" })
public class UpdateDepartmentController implements UpdateDepartmentSwagger {
    private final UpdateDepartmentUseCase useCase;
    private final DepartmentMapper mapper;

    public UpdateDepartmentController(UpdateDepartmentUseCase useCase, DepartmentMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    @PatchMapping("/{departmentId}")
    public Department patchDepartment(@Valid @RequestBody DepartmentRequest request,
            @PathVariable @Positive(message = "User ID must be valid.") Long departmentId) {
        return mapper.toResponse(useCase.update(departmentId, mapper.toModel(request)));
    }
}
