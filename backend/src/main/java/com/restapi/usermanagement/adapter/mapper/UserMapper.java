package com.restapi.usermanagement.adapter.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.restapi.usermanagement.adapter.input.generic.PaginationResponseDTO;
import com.restapi.usermanagement.adapter.input.user.dto.CreateUserRequest;
import com.restapi.usermanagement.adapter.input.user.dto.UpdateUserRequest;
import com.restapi.usermanagement.adapter.input.user.dto.User;
import com.restapi.usermanagement.adapter.output.database.entity.UserEntity;
import com.restapi.usermanagement.domain.model.PaginationResponseModel;
import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.domain.model.UserRequestModel;

@Mapper(componentModel = "spring", uses = DepartmentMapper.class)
public interface UserMapper {

    UserModel toModel(UserEntity entity);

    UserRequestModel toModel(CreateUserRequest request);

    UserRequestModel toModel(UpdateUserRequest request);

    User toResponse(UserModel model);

    PaginationResponseDTO toResponseDTO(PaginationResponseModel model);

    UserEntity toEntity(UserRequestModel model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromModel(UserRequestModel model, @MappingTarget UserEntity entity);
}
