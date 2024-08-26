package com.restapi.usermanagement.domain.service.department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.domain.model.PaginationResponseModel;
import com.restapi.usermanagement.port.department.input.ListDepartmentsPaginatedUseCase;
import com.restapi.usermanagement.port.department.output.ListDepartmentsPaginatedPort;

@Service
public class ListDepartmentsPaginatedService implements ListDepartmentsPaginatedUseCase {
    private final ListDepartmentsPaginatedPort port;

    public ListDepartmentsPaginatedService(ListDepartmentsPaginatedPort port) {
        this.port = port;
    }

    @Override
    public PaginationResponseModel findDepartmentList(Pageable page, Long departmentId, String departmentName) {
        Page<DepartmentModel> users = port.findDepartmentList(page, departmentId, departmentName);

        return new PaginationResponseModel(users.getNumber(), users.getTotalElements(), users.getTotalPages(),
                users.getContent());
    }
}
