package com.employee_management.service.Employee;

import com.employee_management.dtos.Employee.EmployeeDTO;
import com.employee_management.dtos.Employee.EmployeeRequestDTO;
import com.employee_management.dtos.Employee.EmployeeResponseDTO;
import com.employee_management.dtos.Employee.EmployeeManagerDTO;
import com.employee_management.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(EmployeeRequestDTO employeeDTO);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    EmployeeResponseDTO updateEmployeeByID(Long id, EmployeeRequestDTO employeeDTO);
    EmployeeManagerDTO getManagerNameByEmployeeId(Long employeeID);
    List<EmployeeDTO> getEmployeesByManagerId(Long managerId);

}
