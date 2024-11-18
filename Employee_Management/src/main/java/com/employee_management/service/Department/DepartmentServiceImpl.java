package com.employee_management.service.Department;

import com.employee_management.dtos.Department.DepartmentRequestDTO;
import com.employee_management.dtos.Department.DepartmentResponseDTO;
import com.employee_management.dtos.Department.DepartmentWithEmployeesDTO;
import com.employee_management.dtos.Employee.EmployeeDTO;
import com.employee_management.entity.Department;
import com.employee_management.exception.DepartmentNotFoundException;
import com.employee_management.exception.ResourceNotFoundException;
import com.employee_management.repo.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());

        Department savedDepartment = departmentRepository.save(department);
        return new DepartmentResponseDTO(savedDepartment);
    }

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found!"));
        return new DepartmentResponseDTO(department);
    }

    @Override
    public DepartmentResponseDTO updateDepartmentById(Long id, DepartmentRequestDTO departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found!"));

        department.setName(departmentDTO.getName());
        Department updatedDepartment = departmentRepository.save(department);

        return new DepartmentResponseDTO(updatedDepartment);
    }

    @Override
    public DepartmentWithEmployeesDTO getEmployeesByDepartmentId(Long departmentId) {
        Department department = departmentRepository.getDepartmentById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + departmentId + " not found!"));

        List<EmployeeDTO> employeeDTOs = department.getEmployees().stream()
                .map(e -> new EmployeeDTO(e.getId(), e.getName(), e.getPosition()))
                .collect(Collectors.toList());

        return new DepartmentWithEmployeesDTO(department.getName(), employeeDTOs);
    }

}
