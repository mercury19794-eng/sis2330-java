package com.example.empleados.domain.model;

import java.math.BigDecimal;

public class Salary {

    // Guarda el monto; es final porque el Value Object es inmutable
    private final BigDecimal amount;

    // Valida la invariante: el salario nunca puede ser negativo
    public Salary(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
