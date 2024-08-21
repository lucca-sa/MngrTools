package com.restapi.usermanagement.port.user.output;

import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.domain.model.UserRequestModel;

public interface UpdateUserPort {
    UserModel update(Long userId, UserRequestModel model);
}
