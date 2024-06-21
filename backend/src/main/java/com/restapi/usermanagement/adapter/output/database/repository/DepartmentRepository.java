package com.restapi.usermanagement.adapter.output.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.usermanagement.adapter.output.database.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}