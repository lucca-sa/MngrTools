package com.restapi.usermanagement.port.department.output;

import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.domain.model.DepartmentRequestModel;

public interface UpdateDepartmentPort {
    DepartmentModel update(Long departmentId, DepartmentRequestModel model);
}
