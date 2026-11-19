package com.example.empleados.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record EmployeeRequest(
        @NotBlank String name,
        @NotBlank String jobTitle,
        @NotNull @PositiveOrZero BigDecimal salary) {
}
