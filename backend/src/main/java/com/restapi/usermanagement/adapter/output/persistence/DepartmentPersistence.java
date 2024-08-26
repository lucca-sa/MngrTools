package com.restapi.usermanagement.adapter.output.persistence;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.restapi.usermanagement.adapter.exception.customs.DataConflictException;
import com.restapi.usermanagement.adapter.mapper.DepartmentMapper;
import com.restapi.usermanagement.adapter.output.database.entity.DepartmentEntity;
import com.restapi.usermanagement.adapter.output.database.repository.DepartmentRepository;
import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.domain.model.DepartmentRequestModel;
import com.restapi.usermanagement.port.department.output.CreateDepartmentPort;
import com.restapi.usermanagement.port.department.output.FindDepartmentByIdPort;
import com.restapi.usermanagement.port.department.output.UpdateDepartmentPort;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DepartmentPersistence implements CreateDepartmentPort, FindDepartmentByIdPort, UpdateDepartmentPort {
    private final DepartmentMapper mapper;
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentModel findDepartmentInfo(Long departmentId) {
        return mapper.toModel(
                departmentRepository.findById(departmentId)
                        .orElseThrow(() -> new NoSuchElementException("Department not found.")));
    }

    @Override
    public DepartmentModel create(DepartmentRequestModel model) {
        DepartmentEntity newDepartment = mapper.toEntity(model);

        return mapper.toModel(departmentRepository.save(newDepartment));
    }

    public DepartmentModel update(Long departmentId, DepartmentRequestModel model) {
        DepartmentEntity baseDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NoSuchElementException("Department not found."));

        if (!baseDepartment.getUsers().isEmpty()) {
            throw new DataConflictException("Department cannot be edited because it has associated users.");
        }

        baseDepartment.setName(model.getName());
        return mapper.toModel(departmentRepository.save(baseDepartment));
    }
}
