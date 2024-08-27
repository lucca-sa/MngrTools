package com.restapi.usermanagement.adapter.output.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.restapi.usermanagement.adapter.exception.customs.DataConflictException;
import com.restapi.usermanagement.adapter.mapper.DepartmentMapper;
import com.restapi.usermanagement.adapter.output.database.entity.DepartmentEntity;
import com.restapi.usermanagement.adapter.output.database.entity.UserEntity;
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

    @Test
    public void testFindDepartmentInfo_Success() {
        Long departmentId = 1L;
        DepartmentEntity departmentEntity = new DepartmentEntity(departmentId, "HR", null);
        DepartmentModel departmentModel = new DepartmentModel(departmentId, "HR");

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(departmentEntity));
        when(departmentMapper.toModel(departmentEntity)).thenReturn(departmentModel);

        DepartmentModel result = departmentPersistence.findDepartmentInfo(departmentId);

        verify(departmentRepository).findById(departmentId);
        verify(departmentMapper).toModel(departmentEntity);
        assertEquals(departmentModel, result);
    }

    @Test
    public void testFindDepartmentInfo_DepartmentNotFound() {
        Long departmentId = 1L;

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> departmentPersistence.findDepartmentInfo(departmentId));
    }

    @Test
    public void testUpdate_Success() {
        Long departmentId = 1L;
        DepartmentRequestModel requestModel = new DepartmentRequestModel("Updated Department");
        DepartmentEntity departmentEntity = new DepartmentEntity(departmentId, "HR", List.of());
        DepartmentModel updatedModel = new DepartmentModel(departmentId, "Updated Department");

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(departmentEntity));
        when(departmentRepository.save(departmentEntity)).thenReturn(departmentEntity);
        when(departmentMapper.toModel(departmentEntity)).thenReturn(updatedModel);

        DepartmentModel result = departmentPersistence.update(departmentId, requestModel);

        verify(departmentRepository).findById(departmentId);
        verify(departmentRepository).save(departmentEntity);
        verify(departmentMapper).toModel(departmentEntity);
        assertEquals(updatedModel, result);
    }

    @Test
    public void testUpdate_DepartmentHasUsers() {
        Long departmentId = 1L;
        UserEntity user = new UserEntity();
        DepartmentRequestModel requestModel = new DepartmentRequestModel("Updated Department");
        DepartmentEntity departmentEntity = new DepartmentEntity(departmentId, "HR", List.of(user));

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(departmentEntity));

        assertThrows(DataConflictException.class, () -> departmentPersistence.update(departmentId, requestModel));
    }

    @Test
    public void testDelete_Success() {
        Long departmentId = 1L;
        DepartmentEntity departmentEntity = new DepartmentEntity(departmentId, "HR", List.of());

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(departmentEntity));

        departmentPersistence.delete(departmentId);

        verify(departmentRepository).findById(departmentId);
        verify(departmentRepository).deleteById(departmentId);
    }

    @Test
    public void testDelete_DepartmentHasUsers() {
        Long departmentId = 1L;
        UserEntity user = new UserEntity();
        DepartmentEntity departmentEntity = new DepartmentEntity(departmentId, "HR", List.of(user));

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(departmentEntity));

        assertThrows(DataConflictException.class, () -> departmentPersistence.delete(departmentId));
    }

    @Test
    public void testFindDepartmentList_Success() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        DepartmentEntity departmentEntity = new DepartmentEntity(1L, "HR", null);
        Page<DepartmentEntity> departmentPage = new PageImpl<>(List.of(departmentEntity), pageable, 1);

        when(departmentRepository.findList(1L, "HR", pageable)).thenReturn(departmentPage);
        when(departmentMapper.toModel(departmentEntity)).thenReturn(new DepartmentModel(1L, "HR"));

        Page<DepartmentModel> result = departmentPersistence.findDepartmentList(pageable, 1L, "HR");

        verify(departmentRepository).findList(1L, "HR", pageable);
        assertEquals(1, result.getTotalElements());
        assertEquals("HR", result.getContent().get(0).getName());
    }

    @Test
    public void testFindDepartmentList_NoDepartmentsFound() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<DepartmentEntity> emptyPage = new PageImpl<>(List.of(), pageable, 0);

        when(departmentRepository.findList(1L, "HR", pageable)).thenReturn(emptyPage);

        assertThrows(NoSuchElementException.class, () -> departmentPersistence.findDepartmentList(pageable, 1L, "HR"));
    }

}
