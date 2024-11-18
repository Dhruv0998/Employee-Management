package com.employee_management.service.Employee;

import com.employee_management.dtos.Employee.EmployeeDTO;
import com.employee_management.dtos.Employee.EmployeeRequestDTO;
import com.employee_management.dtos.Employee.EmployeeResponseDTO;
import com.employee_management.dtos.Employee.EmployeeManagerDTO;
import com.employee_management.entity.Department;
import com.employee_management.entity.Employee;
import com.employee_management.exception.EmployeeNotFoundException;
import com.employee_management.exception.ManagerNotFoundException;
import com.employee_management.exception.ResourceNotFoundException;
import com.employee_management.repo.DepartmentRepository;
import com.employee_management.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public Employee createEmployee(EmployeeRequestDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setPosition(employeeDTO.getPosition());

        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        employee.setDepartment(department);

        if (employeeDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(employeeDTO.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            employee.setManager(manager);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found!"));
    }

    @Override
    public EmployeeResponseDTO updateEmployeeByID(Long id, EmployeeRequestDTO employeeDTO) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found!"));

        updateEmployee.setName(employeeDTO.getName());
        updateEmployee.setPosition(employeeDTO.getPosition());

        // Set Department
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found!"));
        updateEmployee.setDepartment(department);

        // Set Manager
        Employee manager = employeeRepository.findById(employeeDTO.getManagerId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found!"));
        updateEmployee.setManager(manager);

        employeeRepository.save(updateEmployee);
        return new EmployeeResponseDTO(updateEmployee);
    }

    @Override
    public EmployeeManagerDTO getManagerNameByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.getEmployeeById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + employeeId + " not found!"));

        String managerName = (employee.getManager() != null) ? employee.getManager().getName() : "No Manager is Assigned to " + employee.getName();

        return new EmployeeManagerDTO(employeeId, employee.getName(), managerName);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByManagerId(Long managerId) {
        List<Employee> employees = employeeRepository.findByManagerId(managerId);

        if (employees.isEmpty()) {
            throw new ManagerNotFoundException("No employees found under the manager with ID " + managerId);
        }
        return employees.stream()
                .map(e -> new EmployeeDTO(e.getId(), e.getName(), e.getPosition()))
                .collect(Collectors.toList());
    }

}
