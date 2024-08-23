package com.restapi.usermanagement.adapter.mapper;

import org.mapstruct.Mapper;

import com.restapi.usermanagement.adapter.input.department.dto.CreateDepartmentRequest;
import com.restapi.usermanagement.adapter.input.department.dto.Department;
import com.restapi.usermanagement.adapter.output.database.entity.DepartmentEntity;
import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.domain.model.DepartmentRequestModel;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentModel toModel(DepartmentEntity entity);

    DepartmentRequestModel toModel(CreateDepartmentRequest request);

    Department toResponse(DepartmentModel model);

    DepartmentEntity toEntity(DepartmentRequestModel model);
}
