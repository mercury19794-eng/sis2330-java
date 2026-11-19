package com.example.empleados.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.empleados.domain.model.Employee;
import com.example.empleados.domain.model.Salary;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void blankNameIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Employee(null, " ", "Dev", new Salary(BigDecimal.TEN)));
    }

    @Test
    void raiseSalaryAppliesPercent() {
        Employee e = new Employee(1L, "Ana", "Dev", new Salary(new BigDecimal("1000")));
        e.raiseSalary(new BigDecimal("10"));
        assertEquals(0, new BigDecimal("1100").compareTo(e.getSalary().getAmount()));
    }

    @Test
    void raiseSalaryRejectsNonPositivePercent() {
        Employee e = new Employee(1L, "Ana", "Dev", new Salary(new BigDecimal("1000")));
        assertThrows(IllegalArgumentException.class, () -> e.raiseSalary(BigDecimal.ZERO));
    }
}
