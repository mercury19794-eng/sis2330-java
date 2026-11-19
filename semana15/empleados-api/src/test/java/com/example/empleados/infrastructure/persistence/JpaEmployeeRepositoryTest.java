package com.example.empleados.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.empleados.domain.model.Employee;
import com.example.empleados.domain.model.Salary;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(JpaEmployeeRepository.class)
class JpaEmployeeRepositoryTest {

    // Implementacion real conectada a la base H2 en memoria
    @Autowired
    private JpaEmployeeRepository repository;

    @Test
    void saveAssignsId() {
        Employee saved = repository.save(new Employee(null, "Ana", "Dev", new Salary(new BigDecimal("1500"))));
        assertNotNull(saved.getId());
    }

    @Test
    void findByIdReturnsSavedEmployee() {
        Employee saved = repository.save(new Employee(null, "Luis", "QA", new Salary(new BigDecimal("1200"))));
        Employee found = repository.findById(saved.getId()).orElseThrow();
        assertEquals("Luis", found.getName());
        assertEquals(0, new BigDecimal("1200").compareTo(found.getSalary().getAmount()));
    }

    @Test
    void deleteRemovesEmployee() {
        Employee saved = repository.save(new Employee(null, "Eva", "Ops", new Salary(new BigDecimal("1000"))));
        assertTrue(repository.existsById(saved.getId()));
        repository.deleteById(saved.getId());
        assertFalse(repository.existsById(saved.getId()));
    }
}
