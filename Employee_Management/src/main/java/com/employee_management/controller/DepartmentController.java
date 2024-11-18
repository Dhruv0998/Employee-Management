package com.employee_management.controller;

import com.employee_management.dtos.Department.DepartmentRequestDTO;
import com.employee_management.dtos.Department.DepartmentResponseDTO;
import com.employee_management.dtos.Department.DepartmentWithEmployeesDTO;
import com.employee_management.service.Department.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/createDepartment")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@Valid @RequestBody DepartmentRequestDTO departmentDTO) {
        DepartmentResponseDTO departmentResponse = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(departmentResponse, HttpStatus.CREATED);
    }

    @GetMapping("/getAllDepartments")
    public List<DepartmentResponseDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/getDepartmentByID/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentResponseDTO departmentResponse = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }
    
    @PutMapping("/updateDepartmentByID/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartmentById(
            @PathVariable Long id,
            @RequestBody DepartmentRequestDTO departmentDTO) {
        DepartmentResponseDTO updatedDepartment = departmentService.updateDepartmentById(id, departmentDTO);
        return ResponseEntity.ok(updatedDepartment);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentWithEmployeesDTO> getEmployeesByDepartmentId(@PathVariable Long departmentId) {
        DepartmentWithEmployeesDTO departmentWithEmployees = departmentService.getEmployeesByDepartmentId(departmentId);
        return ResponseEntity.ok(departmentWithEmployees);
    }

}
