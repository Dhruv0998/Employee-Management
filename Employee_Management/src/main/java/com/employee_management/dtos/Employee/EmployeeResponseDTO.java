package com.employee_management.dtos.Employee;

import com.employee_management.entity.Employee;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String position;
    private String departmentName;
    private String managerName;

    public EmployeeResponseDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.position = employee.getPosition();
        this.departmentName = employee.getDepartment().getName();
        this.managerName = employee.getManager() != null ? employee.getManager().getName() : null;
    }

    // Getters and Setters
}

