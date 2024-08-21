package com.restapi.usermanagement.adapter.input.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @Size(max = 70, message = "Name must be at most 70 characters")
    private String name;

    @Email(message = "Email should be valid")
    @Size(max = 50, message = "Email must be at most 50 characters")
    private String email;

    @Size(min = 10, max = 15, message = "Phone number should be between 10 and 15 characters")
    private String phone;

    @Size(max = 100, message = "Address must be at most 100 characters")
    private String address;

    private Long departmentId;
}
