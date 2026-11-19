package com.example.empleados.domain.model;

import java.math.BigDecimal;

public class Employee {

    // Datos del empleado; el id es null hasta que se guarda
    private final Long id;
    private final String name;
    private final String jobTitle;
    private Salary salary;

    // Valida que nombre, cargo y salario existan
    public Employee(Long id, String name, String jobTitle, Salary salary) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (jobTitle == null || jobTitle.isBlank()) {
            throw new IllegalArgumentException("Job title is required");
        }
        if (salary == null) {
            throw new IllegalArgumentException("Salary is required");
        }
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public void raiseSalary(BigDecimal percent) {
        if (percent == null || percent.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Percent must be positive");
        }
        BigDecimal factor = BigDecimal.ONE.add(percent.divide(BigDecimal.valueOf(100)));
        this.salary = new Salary(salary.getAmount().multiply(factor));
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getJobTitle() { return jobTitle; }
    public Salary getSalary() { return salary; }
}
