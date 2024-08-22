package com.restapi.usermanagement.domain.service.user;

import org.springframework.stereotype.Service;

import com.restapi.usermanagement.port.user.input.DeleteUserUseCase;
import com.restapi.usermanagement.port.user.output.DeleteUserPort;

@Service
public class DeleteUserService implements DeleteUserUseCase {
    private final DeleteUserPort port;

    public DeleteUserService(DeleteUserPort port) {
        this.port = port;
    }

    @Override
    public void delete(Long userId) {
        port.delete(userId);
    }
}
