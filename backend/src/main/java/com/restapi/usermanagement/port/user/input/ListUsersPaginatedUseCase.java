package com.restapi.usermanagement.port.user.input;

import org.springframework.data.domain.Pageable;

import com.restapi.usermanagement.domain.model.PaginationResponseModel;

public interface ListUsersPaginatedUseCase {
    PaginationResponseModel findUserList(Pageable page, Long userId, String userName, String departmentName,
            Long departmentId);
}
