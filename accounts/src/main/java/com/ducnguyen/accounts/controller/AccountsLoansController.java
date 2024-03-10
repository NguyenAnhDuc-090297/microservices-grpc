package com.ducnguyen.accounts.controller;

import com.ducnguyen.accounts.dto.LoanDto;
import com.ducnguyen.accounts.service.AccountsLoansService;
import common.grpc.loans.Loan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grpc")
@Slf4j
@Validated
public class AccountsLoansController {

    private final AccountsLoansService accountsLoansService;

    public AccountsLoansController(AccountsLoansService accountsLoansService) {
        this.accountsLoansService = accountsLoansService;
    }

    @PostMapping("/loan")
    public ResponseEntity<String> createLoan(@RequestBody LoanDto loanDto) {
        String loanId = accountsLoansService.createLoan(loanDto);
        return new ResponseEntity<>(loanId, HttpStatus.CREATED);
    }

    @GetMapping("/loan")
    public ResponseEntity<Loan> readLoan(@RequestParam String guid) {
        Loan loan = accountsLoansService.readLoan(guid);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
}
