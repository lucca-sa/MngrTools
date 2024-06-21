package com.restapi.usermanagement.adapter.mapper;

import org.mapstruct.Mapper;

import com.restapi.usermanagement.adapter.input.user.dto.CreateUserRequest;
import com.restapi.usermanagement.adapter.input.user.dto.UserResponse;
import com.restapi.usermanagement.adapter.output.database.entity.UserEntity;
import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.domain.model.UserRequestModel;

@Mapper(componentModel = "spring", uses = DepartmentMapper.class)
public interface UserMapper {
    UserModel toModel(UserEntity entity);

    UserResponse toResponse(UserModel model);

    UserRequestModel requestToModel(CreateUserRequest request);

    UserEntity toEntity(UserRequestModel model);
}
