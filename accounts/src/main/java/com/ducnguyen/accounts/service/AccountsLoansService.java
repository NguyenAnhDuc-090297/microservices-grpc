package com.ducnguyen.accounts.service;

import com.ducnguyen.accounts.dto.LoanDto;
import common.grpc.loans.Borrower;
import common.grpc.loans.Loan;
import common.grpc.loans.LoanId;
import common.grpc.loans.LoansServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountsLoansService {

    @GrpcClient("grpc-accounts-loans-service")
    LoansServiceGrpc.LoansServiceBlockingStub synchronousClient;

    public String createLoan(LoanDto loanDto) {

        Borrower borrower = Borrower.newBuilder()
                .setName(loanDto.borrower().name())
                .setAge(loanDto.borrower().age())
                .setAnnualIncome(loanDto.borrower().annualIncome().doubleValue())
                .setAnnualDebt(loanDto.borrower().annualDebt().doubleValue())
                .setDelinquentDebt(loanDto.borrower().delinquentDebt())
                .build();

        Loan loan = Loan.newBuilder()
                .setGuid(loanDto.guid())
                .setRequestedAmount(loanDto.requestedAmount())
                .setTermMonths(loanDto.termMonths())
                .setAnnualInterest(loanDto.annualInterest())
                .setBorrower(borrower)
                .build();

        LoanId loanId = synchronousClient.createLoan(loan);
        return loanId.getGuid();
    }

    public Loan readLoan(String guid) {
        LoanId loanId = LoanId.newBuilder().setGuid(guid).build();
        return synchronousClient.readLoan(loanId);
    }
}
