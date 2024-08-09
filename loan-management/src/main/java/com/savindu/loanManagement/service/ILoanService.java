package com.savindu.loanManagement.service;


import com.savindu.loanManagement.dto.LoanDto;

public interface ILoanService {
    void createLoan(String nicNumber);

    LoanDto fetchLoan(String nicNumber);


    boolean updateLoan(LoanDto loansDto);


    boolean deleteLoan(String nicNumber);

}
