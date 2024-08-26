package com.restapi.usermanagement.port.department.input;

import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.domain.model.DepartmentRequestModel;

public interface UpdateDepartmentUseCase {
    DepartmentModel update(Long userId, DepartmentRequestModel model);
}
