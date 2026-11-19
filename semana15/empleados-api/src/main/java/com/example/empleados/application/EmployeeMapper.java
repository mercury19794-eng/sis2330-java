package com.example.empleados.application;

import com.example.empleados.domain.model.Employee;
import com.example.empleados.domain.model.Salary;

public final class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static Employee toDomain(Long id, EmployeeRequest request) {
        return new Employee(id, request.name(), request.jobTitle(), new Salary(request.salary()));
    }

    public static EmployeeResponse toResponse(Employee e) {
        return new EmployeeResponse(e.getId(), e.getName(), e.getJobTitle(), e.getSalary().getAmount());
    }
}
