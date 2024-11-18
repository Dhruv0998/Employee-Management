package com.employee_management.dtos.Employee;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeRequestDTO {

    @NotBlank(message = "Employee name is mandatory!")
    private String name;

    @NotBlank(message = "Position is mandatory!")
    private String position;

    private Long departmentId;
    private Long managerId;

}

