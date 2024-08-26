package com.restapi.usermanagement.adapter.input.department.swagger;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestParam;

import com.restapi.usermanagement.adapter.exception.ErrorResponse;
import com.restapi.usermanagement.adapter.input.generic.PaginationResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Departments")
public interface ListDepartmentsPaginatedSwagger {
        @Operation(summary = "List Departments Paginated")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Departments found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PaginationResponseDTO.class))),
                        @ApiResponse(description = "Error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorResponse.class))),

        })
        public PaginationResponseDTO getDepartmentList(@PageableDefault(page = 0, size = 10) Pageable page,
                        @RequestParam(required = false) Long departmentId,
                        @RequestParam(required = false) String departmentName);
}
