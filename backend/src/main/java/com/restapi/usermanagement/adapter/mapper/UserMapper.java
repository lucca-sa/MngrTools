package com.restapi.usermanagement.adapter.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.restapi.usermanagement.adapter.input.generic.PaginationResponseDTO;
import com.restapi.usermanagement.adapter.input.user.dto.CreateUserRequest;
import com.restapi.usermanagement.adapter.input.user.dto.UpdateUserRequest;
import com.restapi.usermanagement.adapter.input.user.dto.UserResponse;
import com.restapi.usermanagement.adapter.output.database.entity.UserEntity;
import com.restapi.usermanagement.domain.model.PaginationResponseModel;
import com.restapi.usermanagement.domain.model.UserModel;
import com.restapi.usermanagement.domain.model.UserRequestModel;

@Mapper(componentModel = "spring", uses = DepartmentMapper.class)
public interface UserMapper {

    UserModel toModel(UserEntity entity);

    UserResponse toResponse(UserModel model);

    UserRequestModel requestToModel(CreateUserRequest request);

    UserRequestModel requestToModel(UpdateUserRequest request);

    UserEntity toEntity(UserRequestModel model);

    PaginationResponseDTO toResponseDTO(PaginationResponseModel model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromModel(UserRequestModel model, @MappingTarget UserEntity entity);
}
