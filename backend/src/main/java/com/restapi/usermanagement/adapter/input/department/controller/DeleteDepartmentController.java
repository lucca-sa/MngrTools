package com.restapi.usermanagement.adapter.input.department.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.usermanagement.adapter.input.department.swagger.DeleteDepartmentSwagger;
import com.restapi.usermanagement.port.department.input.DeleteDepartmentUseCase;

@RestController
@RequestMapping(value = "/api/department/", produces = { "application/json" })
public class DeleteDepartmentController implements DeleteDepartmentSwagger {
    private final DeleteDepartmentUseCase useCase;

    public DeleteDepartmentController(DeleteDepartmentUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    @DeleteMapping("{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentId) {
        useCase.delete(departmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
