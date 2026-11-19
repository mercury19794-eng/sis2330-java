package com.example.empleados.application;

import com.example.empleados.domain.exception.EmployeeNotFoundException;
import com.example.empleados.domain.model.Employee;
import com.example.empleados.domain.repository.EmployeeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    // Puntero a la INTERFAZ del dominio, no a la implementacion JPA
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeResponse create(EmployeeRequest request) {
        Employee saved = repository.save(EmployeeMapper.toDomain(null, request));
        return EmployeeMapper.toResponse(saved);
    }

    public EmployeeResponse findById(Long id) {
        return repository.findById(id)
                .map(EmployeeMapper::toResponse)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public List<EmployeeResponse> findAll() {
        // Lista de respuestas armada desde los empleados del dominio
        return repository.findAll().stream().map(EmployeeMapper::toResponse).toList();
    }

    public EmployeeResponse update(Long id, EmployeeRequest request) {
        if (!repository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        Employee saved = repository.save(EmployeeMapper.toDomain(id, request));
        return EmployeeMapper.toResponse(saved);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
