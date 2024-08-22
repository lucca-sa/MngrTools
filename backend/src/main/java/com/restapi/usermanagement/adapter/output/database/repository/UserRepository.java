package com.restapi.usermanagement.adapter.output.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restapi.usermanagement.adapter.output.database.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("""
            SELECT u FROM UserEntity u
            LEFT JOIN u.department d
            WHERE (:userId IS NULL OR u.id = :userId)
            AND (COALESCE(:userName, '') = '' OR LOWER(u.name) LIKE LOWER(CONCAT('%', COALESCE(CAST(:userName AS string), ''), '%')))
            AND (COALESCE(:departmentName, '') = '' OR LOWER(d.name) LIKE LOWER(CONCAT('%', COALESCE(CAST(:departmentName AS string), ''), '%')))
            """)
    Page<UserEntity> findList(
            @Param("userId") Long userId,
            @Param("userName") String userName,
            @Param("departmentName") String departmentName,
            Pageable pageable);
}
