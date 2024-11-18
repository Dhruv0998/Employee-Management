package com.employee_management.dtos.Department;

import com.employee_management.dtos.Employee.EmployeeDTO;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentWithEmployeesDTO {

    private String departmentName;
    private List<EmployeeDTO> employees;

}
