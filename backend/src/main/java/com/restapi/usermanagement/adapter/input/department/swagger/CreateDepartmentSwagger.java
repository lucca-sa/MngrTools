package com.restapi.usermanagement.adapter.input.department.swagger;

import org.springframework.web.bind.annotation.RequestBody;

import com.restapi.usermanagement.adapter.exception.ErrorResponse;
import com.restapi.usermanagement.adapter.input.department.dto.CreateDepartmentRequest;
import com.restapi.usermanagement.adapter.input.department.dto.DepartmentResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Departments")
public interface CreateDepartmentSwagger {

    @Operation(summary = "Create a Department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = DepartmentResponse.class))),
            @ApiResponse(description = "Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class))),

    })
    public DepartmentResponse postNewDepartment(@Valid @RequestBody CreateDepartmentRequest request);
}
