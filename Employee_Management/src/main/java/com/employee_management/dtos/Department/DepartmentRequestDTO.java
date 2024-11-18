package com.employee_management.dtos.Department;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DepartmentRequestDTO {

    @NotBlank(message = "Department name is mandatory!")
    private String name;

}
