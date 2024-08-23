package com.restapi.usermanagement.adapter.input.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 70, message = "Name must be at most 70 characters")
    private String name;
}
