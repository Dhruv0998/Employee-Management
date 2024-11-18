package com.employee_management.dtos.Department;

import com.employee_management.entity.Department;
import com.employee_management.entity.Employee;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
public class DepartmentResponseDTO {

    private Long id;
    private String name;
    private List<String> employeeNames;

    public DepartmentResponseDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.employeeNames = department.getEmployees() != null ?
                department.getEmployees().stream()
                        .map(Employee::getName)
                        .collect(Collectors.toList()) :
                new ArrayList<>();
    }

}
