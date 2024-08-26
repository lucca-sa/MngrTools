package com.restapi.usermanagement.port.department.output;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.restapi.usermanagement.domain.model.DepartmentModel;

public interface ListDepartmentsPaginatedPort {
    Page<DepartmentModel> findDepartmentList(Pageable page, Long departmentId, String departmentName);
}
