package com.example.empleados.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.empleados.domain.model.Salary;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class SalaryTest {

    @Test
    void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Salary(new BigDecimal("-1")));
    }

    @Test
    void rejectsNullAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Salary(null));
    }

    @Test
    void acceptsZero() {
        Salary salary = new Salary(BigDecimal.ZERO);
        assertEquals(0, BigDecimal.ZERO.compareTo(salary.getAmount()));
    }
}
