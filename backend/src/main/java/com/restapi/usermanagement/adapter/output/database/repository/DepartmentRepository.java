package com.restapi.usermanagement.adapter.output.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restapi.usermanagement.adapter.output.database.entity.DepartmentEntity;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
        @Query("""
            SELECT d FROM DepartmentEntity d
            WHERE (:departmentId IS NULL OR d.id = :departmentId)
            AND (COALESCE(:departmentName, '') = '' OR LOWER(d.name) LIKE LOWER(CONCAT('%', COALESCE(CAST(:departmentName AS string), ''), '%')))
            """)
    Page<DepartmentEntity> findList(
            @Param("departmentId") Long departmentId,
            @Param("departmentName") String departmentName,
            Pageable pageable);
}