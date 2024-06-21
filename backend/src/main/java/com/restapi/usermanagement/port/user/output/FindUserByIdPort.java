package com.restapi.usermanagement.port.user.output;

import com.restapi.usermanagement.domain.model.UserModel;

public interface FindUserByIdPort {
    UserModel findUserInfo(Long userId);
}
