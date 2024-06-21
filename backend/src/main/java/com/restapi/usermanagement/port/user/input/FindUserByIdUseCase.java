package com.restapi.usermanagement.port.user.input;

import com.restapi.usermanagement.domain.model.UserModel;

public interface FindUserByIdUseCase {
    UserModel findUserInfo(Long userId);
}
