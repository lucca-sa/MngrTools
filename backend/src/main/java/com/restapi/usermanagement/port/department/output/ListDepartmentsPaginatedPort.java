package com.restapi.usermanagement.port.department.output;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.restapi.usermanagement.domain.model.DepartmentListModel;

public interface ListDepartmentsPaginatedPort {
    Page<DepartmentListModel> findDepartmentList(Pageable page, Long departmentId, String departmentName);
}
