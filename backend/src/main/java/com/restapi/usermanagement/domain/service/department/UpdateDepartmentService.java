package com.restapi.usermanagement.domain.service.department;

import org.springframework.stereotype.Service;

import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.domain.model.DepartmentRequestModel;
import com.restapi.usermanagement.port.department.input.UpdateDepartmentUseCase;
import com.restapi.usermanagement.port.department.output.UpdateDepartmentPort;

@Service
public class UpdateDepartmentService implements UpdateDepartmentUseCase {
    private final UpdateDepartmentPort port;

    public UpdateDepartmentService(UpdateDepartmentPort port) {
        this.port = port;
    }

    @Override
    public DepartmentModel update(Long departmentId, DepartmentRequestModel model) {
        return port.update(departmentId, model);
    }
}
