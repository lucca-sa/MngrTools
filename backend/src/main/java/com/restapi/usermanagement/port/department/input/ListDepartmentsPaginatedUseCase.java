package com.restapi.usermanagement.port.department.input;

import org.springframework.data.domain.Pageable;

import com.restapi.usermanagement.domain.model.PaginationResponseModel;

public interface ListDepartmentsPaginatedUseCase {
    PaginationResponseModel findDepartmentList(Pageable page, Long departmentId, String departmentName);
}
