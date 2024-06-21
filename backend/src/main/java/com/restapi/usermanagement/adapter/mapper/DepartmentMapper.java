package com.restapi.usermanagement.adapter.mapper;

import org.mapstruct.Mapper;

import com.restapi.usermanagement.adapter.input.user.dto.DepartmentDto;
import com.restapi.usermanagement.adapter.output.database.entity.DepartmentEntity;
import com.restapi.usermanagement.domain.model.DepartmentModel;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto toDto(DepartmentModel model);
    DepartmentModel toModel(DepartmentEntity entity);
}
