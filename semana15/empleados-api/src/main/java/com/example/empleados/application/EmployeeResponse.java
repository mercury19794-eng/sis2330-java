package com.example.empleados.application;

import java.math.BigDecimal;

public record EmployeeResponse(Long id, String name, String jobTitle, BigDecimal salary) {
}
