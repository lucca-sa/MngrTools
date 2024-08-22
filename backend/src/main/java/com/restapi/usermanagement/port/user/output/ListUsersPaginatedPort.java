package com.restapi.usermanagement.port.user.output;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.restapi.usermanagement.domain.model.UserModel;

public interface ListUsersPaginatedPort {
    Page<UserModel> findUserList(Pageable page, Long userId, String userName, String departmentName);
}
