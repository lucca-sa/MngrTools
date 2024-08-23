package com.restapi.usermanagement.domain.service.department;

import org.springframework.stereotype.Service;

import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.domain.model.DepartmentRequestModel;
import com.restapi.usermanagement.port.department.input.CreateDepartmentUseCase;
import com.restapi.usermanagement.port.department.output.CreateDepartmentPort;

@Service
public class CreateDepartmentService implements CreateDepartmentUseCase {
    private final CreateDepartmentPort port;

    public CreateDepartmentService(CreateDepartmentPort port) {
        this.port = port;
    }

    @Override
    public DepartmentModel create(DepartmentRequestModel model) {
        return port.create(model);
    }
}
