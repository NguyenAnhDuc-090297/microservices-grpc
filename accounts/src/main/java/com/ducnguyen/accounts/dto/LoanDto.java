package com.ducnguyen.accounts.dto;

public record LoanDto(String guid,
        double requestedAmount,
        int termMonths,
        float annualInterest,
        BorrowerDto borrower) {
}


