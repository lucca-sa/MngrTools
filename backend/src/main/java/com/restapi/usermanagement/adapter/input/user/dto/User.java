package com.restapi.usermanagement.adapter.input.user.dto;

import com.restapi.usermanagement.adapter.input.department.dto.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Department department;
}
