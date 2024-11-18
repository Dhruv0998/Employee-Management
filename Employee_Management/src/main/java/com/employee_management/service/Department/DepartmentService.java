package com.employee_management.service.Department;

import com.employee_management.dtos.Department.DepartmentRequestDTO;
import com.employee_management.dtos.Department.DepartmentResponseDTO;
import com.employee_management.dtos.Department.DepartmentWithEmployeesDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentDTO);
    List<DepartmentResponseDTO> getAllDepartments();
    DepartmentResponseDTO getDepartmentById(Long id);
    DepartmentResponseDTO updateDepartmentById(Long id, DepartmentRequestDTO departmentDTO);
    DepartmentWithEmployeesDTO getEmployeesByDepartmentId(Long departmentId);

}
