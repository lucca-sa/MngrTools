package com.restapi.usermanagement.port.department.input;

import com.restapi.usermanagement.domain.model.DepartmentModel;

public interface FindDepartmentByIdUseCase {
    DepartmentModel findDepartmentInfo(Long departmentId);
}
