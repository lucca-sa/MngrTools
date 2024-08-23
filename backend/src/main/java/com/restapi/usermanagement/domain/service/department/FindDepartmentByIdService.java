package com.restapi.usermanagement.domain.service.department;

import org.springframework.stereotype.Service;

import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.port.department.input.FindDepartmentByIdUseCase;
import com.restapi.usermanagement.port.department.output.FindDepartmentByIdPort;

@Service
public class FindDepartmentByIdService implements FindDepartmentByIdUseCase {
    private final FindDepartmentByIdPort port;

    public FindDepartmentByIdService(FindDepartmentByIdPort port) {
        this.port = port;
    }

    @Override
    public DepartmentModel findDepartmentInfo(Long departmentId) {
        return port.findDepartmentInfo(departmentId);
    }
}
