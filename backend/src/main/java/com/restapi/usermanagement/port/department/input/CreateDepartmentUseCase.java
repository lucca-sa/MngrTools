package com.restapi.usermanagement.port.department.input;

import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.domain.model.DepartmentRequestModel;

public interface CreateDepartmentUseCase {
    DepartmentModel create(DepartmentRequestModel model);
}
