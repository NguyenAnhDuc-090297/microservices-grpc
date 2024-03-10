package com.ducnguyen.loans.service;

import common.grpc.loans.Borrower;
import common.grpc.loans.Loan;
import common.grpc.loans.LoanId;
import common.grpc.loans.LoansServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class LoansService extends LoansServiceGrpc.LoansServiceImplBase {

    @Override
    public void createLoan(Loan loan, StreamObserver<LoanId> responseObserver) {
        String savedLoanId = this.saveLoan(loan);
        responseObserver.onNext(LoanId.newBuilder().setGuid(savedLoanId).build());
        responseObserver.onCompleted();
    }

    private String saveLoan(Loan loan) {
        // save loan to the database, then return loanId
        log.info("HI MOM!");
        log.info("loanId = {}", loan.getGuid());
        return loan.getGuid();
    }

    @Override
    public void readLoan(LoanId loanId, StreamObserver<Loan> responseObserver) {
        Loan loan = this.getLoan(loanId.getGuid());
        responseObserver.onNext(loan);
        responseObserver.onCompleted();
    }

    private Loan getLoan(String loanId) {
        // read loan from the database, then return loan
        Borrower borrower = Borrower.newBuilder()
                .setName("Alita")
                .setAge(25)
                .setAnnualIncome(65000)
                .setAnnualDebt(8656)
                .setDelinquentDebt(false)
                .build();

        return Loan.newBuilder()
                .setGuid(loanId)
                .setRequestedAmount(8000)
                .setTermMonths(12)
                .setAnnualInterest(8.2f)
                .setBorrower(borrower)
                .build();
    }
}
