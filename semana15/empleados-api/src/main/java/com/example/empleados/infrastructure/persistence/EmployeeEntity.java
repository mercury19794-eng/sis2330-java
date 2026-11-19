package com.example.empleados.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

    // Columnas de la tabla employees
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String jobTitle;
    private BigDecimal salary;

    // JPA exige un constructor vacio
    protected EmployeeEntity() {
    }

    public EmployeeEntity(Long id, String name, String jobTitle, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getJobTitle() { return jobTitle; }
    public BigDecimal getSalary() { return salary; }
}
