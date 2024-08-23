package com.restapi.usermanagement.port.department.output;

import com.restapi.usermanagement.domain.model.DepartmentModel;

public interface FindDepartmentByIdPort {
    DepartmentModel findDepartmentInfo(Long departmentId);
}
