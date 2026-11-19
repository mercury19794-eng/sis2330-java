package com.example.empleados.infrastructure.persistence;

import com.example.empleados.domain.model.Employee;
import com.example.empleados.domain.model.Salary;
import com.example.empleados.domain.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEmployeeRepository implements EmployeeRepository {

    // Puntero al repository de Spring Data; Spring lo inyecta por el constructor
    private final SpringDataEmployeeRepository springData;

    public JpaEmployeeRepository(SpringDataEmployeeRepository springData) {
        this.springData = springData;
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity saved = springData.save(toEntity(employee));
        return toDomain(saved);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return springData.findById(id).map(this::toDomain);
    }

    @Override
    public List<Employee> findAll() {
        // Lista nueva de objetos de dominio, convertidos desde las entidades JPA
        return springData.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        springData.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springData.existsById(id);
    }

    private EmployeeEntity toEntity(Employee e) {
        return new EmployeeEntity(e.getId(), e.getName(), e.getJobTitle(), e.getSalary().getAmount());
    }

    private Employee toDomain(EmployeeEntity e) {
        return new Employee(e.getId(), e.getName(), e.getJobTitle(), new Salary(e.getSalary()));
    }
}
