package com.employee_management.controller;

import com.employee_management.dtos.Employee.EmployeeDTO;
import com.employee_management.dtos.Employee.EmployeeRequestDTO;
import com.employee_management.dtos.Employee.EmployeeResponseDTO;
import com.employee_management.dtos.Employee.EmployeeManagerDTO;
import com.employee_management.entity.Employee;
import com.employee_management.service.Employee.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeDTO) {
        Employee employee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(new EmployeeResponseDTO(employee), HttpStatus.CREATED);
    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(EmployeeResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/getEmployeeByID/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(new EmployeeResponseDTO(employee), HttpStatus.OK);
    }

    @PutMapping("/updateEmployeeByID/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployeeByID(@PathVariable Long id, @RequestBody EmployeeRequestDTO employeeDTO) {
        EmployeeResponseDTO updatedEmployee = employeeService.updateEmployeeByID(id, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/{id}/manager")
    public ResponseEntity<EmployeeManagerDTO> getManagerNameByEmployeeId(@PathVariable Long id) {
        EmployeeManagerDTO employeeManagerDTO = employeeService.getManagerNameByEmployeeId(id);
        return ResponseEntity.ok(employeeManagerDTO);
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByManagerId(@PathVariable Long managerId) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByManagerId(managerId);
        return ResponseEntity.ok(employees);
    }

}
