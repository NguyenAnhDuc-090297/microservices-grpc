package com.ducnguyen.accounts.dto;

import java.math.BigDecimal;

public record BorrowerDto(
        String name,
        int age,
        BigDecimal annualIncome,
        BigDecimal annualDebt,
        boolean delinquentDebt) {
}
