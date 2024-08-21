package com.restapi.usermanagement.port.user.input;

import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.domain.model.UserRequestModel;

public interface UpdateUserUseCase {
    UserModel update(Long userId, UserRequestModel model);
}
