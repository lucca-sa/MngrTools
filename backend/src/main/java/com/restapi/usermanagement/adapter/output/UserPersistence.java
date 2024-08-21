package com.restapi.usermanagement.adapter.output;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.restapi.usermanagement.adapter.mapper.UserMapper;
import com.restapi.usermanagement.adapter.output.database.entity.DepartmentEntity;
import com.restapi.usermanagement.adapter.output.database.entity.UserEntity;
import com.restapi.usermanagement.adapter.output.database.repository.DepartmentRepository;
import com.restapi.usermanagement.adapter.output.database.repository.UserRepository;
import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.domain.model.UserRequestModel;
import com.restapi.usermanagement.port.user.output.CreateUserPort;
import com.restapi.usermanagement.port.user.output.FindUserByIdPort;
import com.restapi.usermanagement.port.user.output.UpdateUserPort;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserPersistence implements FindUserByIdPort, CreateUserPort, UpdateUserPort {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public UserModel findUserInfo(Long userId) {
        return userMapper.toModel(
                userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found.")));
    }

    @Override
    public UserModel create(UserRequestModel model) {
        UserEntity newUser = userMapper.toEntity(model);

        DepartmentEntity userDepartment = departmentRepository.findById(model.getDepartmentId())
                .orElseThrow(() -> new NoSuchElementException("Department not found."));

        newUser.setDepartment(userDepartment);

        return userMapper.toModel(userRepository.save(newUser));
    }

    @Override
    public UserModel update(Long userId, UserRequestModel model) {

        UserEntity baseUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found."));

        if (model.getDepartmentId() != null) {
            DepartmentEntity userDepartment = departmentRepository.findById(model.getDepartmentId())
                    .orElseThrow(() -> new NoSuchElementException("Department not found."));
            baseUser.setDepartment(userDepartment);
        }

        userMapper.updateUserFromModel(model, baseUser);
        return userMapper.toModel(userRepository.save(baseUser));
    }
}
