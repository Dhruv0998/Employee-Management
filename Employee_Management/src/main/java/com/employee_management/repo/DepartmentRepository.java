package com.employee_management.repo;

import com.employee_management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> getDepartmentById(Long departmentId);

}

