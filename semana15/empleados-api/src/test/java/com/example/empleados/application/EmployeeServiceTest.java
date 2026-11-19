package com.example.empleados.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.empleados.domain.exception.EmployeeNotFoundException;
import com.example.empleados.domain.model.Employee;
import com.example.empleados.domain.model.Salary;
import com.example.empleados.domain.repository.EmployeeRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    // Repository falso: el test no toca la base de datos
    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @Test
    void createReturnsResponseWithId() {
        when(repository.save(any(Employee.class))).thenAnswer(inv -> {
            Employee e = inv.getArgument(0);
            return new Employee(1L, e.getName(), e.getJobTitle(), e.getSalary());
        });
        EmployeeResponse r = service.create(new EmployeeRequest("Ana", "Dev", new BigDecimal("1000")));
        assertEquals(1L, r.id());
        assertEquals("Ana", r.name());
    }

    @Test
    void findByIdThrowsWhenMissing() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    void findAllReturnsMappedList() {
        when(repository.findAll()).thenReturn(
                List.of(new Employee(1L, "Ana", "Dev", new Salary(BigDecimal.TEN))));
        assertEquals(1, service.findAll().size());
    }

    @Test
    void deleteRemovesExistingEmployee() {
        when(repository.existsById(1L)).thenReturn(true);
        service.delete(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteThrowsWhenMissing() {
        when(repository.existsById(5L)).thenReturn(false);
        assertThrows(EmployeeNotFoundException.class, () -> service.delete(5L));
        verify(repository, never()).deleteById(any());
    }
}
