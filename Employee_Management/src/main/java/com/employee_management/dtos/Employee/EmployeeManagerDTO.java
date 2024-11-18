package com.employee_management.dtos.Employee;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeManagerDTO {

    private Long employeeId;
    private String employeeName;
    private String managerName;

}
