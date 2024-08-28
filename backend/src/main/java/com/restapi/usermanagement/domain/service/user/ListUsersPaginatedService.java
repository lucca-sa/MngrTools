package com.restapi.usermanagement.domain.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.restapi.usermanagement.domain.model.PaginationResponseModel;
import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.port.user.input.ListUsersPaginatedUseCase;
import com.restapi.usermanagement.port.user.output.ListUsersPaginatedPort;

@Service
public class ListUsersPaginatedService implements ListUsersPaginatedUseCase {
    private final ListUsersPaginatedPort port;

    public ListUsersPaginatedService(ListUsersPaginatedPort port) {
        this.port = port;
    }

    @Override
    public PaginationResponseModel findUserList(Pageable page, Long userId, String userName, String departmentName,
            Long departmentId) {
        Page<UserModel> users = port.findUserList(page, userId, userName, departmentName, departmentId);

        return new PaginationResponseModel(users.getNumber(), users.getTotalElements(), users.getTotalPages(),
                users.getContent());
    }
}
