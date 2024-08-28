package com.restapi.usermanagement.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.restapi.usermanagement.adapter.input.department.dto.Department;
import com.restapi.usermanagement.adapter.input.department.dto.DepartmentRequest;
import com.restapi.usermanagement.adapter.output.database.entity.DepartmentEntity;
import com.restapi.usermanagement.adapter.output.database.entity.UserEntity;
import com.restapi.usermanagement.domain.model.DepartmentListModel;
import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.domain.model.DepartmentRequestModel;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentModel toModel(DepartmentEntity entity);

    @Mapping(source = "users", target = "hasUsers", qualifiedByName = "mapHasUsers")
    DepartmentListModel toListModel(DepartmentEntity entity);

    DepartmentRequestModel toModel(DepartmentRequest request);

    Department toResponse(DepartmentModel model);

    DepartmentEntity toEntity(DepartmentRequestModel model);

    @Named("mapHasUsers")
    default Boolean mapHasUsers(List<UserEntity> users) {
        return users != null && !users.isEmpty();
    }
}
