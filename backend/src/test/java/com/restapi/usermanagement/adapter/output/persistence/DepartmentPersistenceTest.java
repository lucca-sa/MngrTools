package com.restapi.usermanagement.adapter.output.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.restapi.usermanagement.adapter.mapper.DepartmentMapper;
import com.restapi.usermanagement.adapter.output.database.entity.DepartmentEntity;
import com.restapi.usermanagement.adapter.output.database.repository.DepartmentRepository;
import com.restapi.usermanagement.domain.model.DepartmentModel;
import com.restapi.usermanagement.domain.model.DepartmentRequestModel;

public class DepartmentPersistenceTest {

        @Mock
        private DepartmentMapper departmentMapper;

        @Mock
        private DepartmentRepository departmentRepository;

        @InjectMocks
        private DepartmentPersistence departmentPersistence;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testCreate_Success() {
                DepartmentRequestModel requestModel = new DepartmentRequestModel("HR");
                DepartmentEntity departmentEntity = new DepartmentEntity(1L, "HR", null);
                DepartmentModel departmentModel = new DepartmentModel(1L, "HR");

                when(departmentMapper.toEntity(requestModel)).thenReturn(departmentEntity);
                when(departmentRepository.save(departmentEntity)).thenReturn(departmentEntity);
                when(departmentMapper.toModel(departmentEntity)).thenReturn(departmentModel);

                DepartmentModel result = departmentPersistence.create(requestModel);

                verify(departmentMapper).toEntity(requestModel);
                verify(departmentRepository).save(departmentEntity);
                verify(departmentMapper).toModel(departmentEntity);
                assertEquals(departmentModel, result);
        }
}
