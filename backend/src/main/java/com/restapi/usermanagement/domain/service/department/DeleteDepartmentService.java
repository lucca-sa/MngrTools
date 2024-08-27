package com.restapi.usermanagement.domain.service.department;

import org.springframework.stereotype.Service;

import com.restapi.usermanagement.port.department.input.DeleteDepartmentUseCase;
import com.restapi.usermanagement.port.department.output.DeleteDepartmentPort;

@Service
public class DeleteDepartmentService implements DeleteDepartmentUseCase {
    private final DeleteDepartmentPort port;

    public DeleteDepartmentService(DeleteDepartmentPort port) {
        this.port = port;
    }

    @Override
    public void delete(Long departmentId) {
        port.delete(departmentId);
    }
}
