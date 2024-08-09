package com.savindu.loanManagement.service.impl;

import com.savindu.loanManagement.constant.LoanConstant;
import com.savindu.loanManagement.dto.LoanDto;
import com.savindu.loanManagement.entity.Loan;
import com.savindu.loanManagement.exception.LoanAlreadyExistsException;
import com.savindu.loanManagement.exception.ResourceNotFoundException;
import com.savindu.loanManagement.mapper.LoanMapper;
import com.savindu.loanManagement.repository.LoanRepository;
import com.savindu.loanManagement.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ILoanServiceImpl implements ILoanService {
    @Autowired
    private LoanRepository loansRepository;
    @Override
    public void createLoan(String nicNumber) {
        Optional<Loan> optionalLoan= loansRepository.findByNicNumber(nicNumber);
        if(optionalLoan.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given nicNumber "+nicNumber);
        }
        loansRepository.save(createNewLoan(nicNumber));
    }
    private Loan createNewLoan(String nicNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setNicNumber(nicNumber);
        newLoan.setLoanType(LoanConstant.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstant.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstant.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoanDto fetchLoan(String nicNumber) {
        Loan loans = loansRepository.findByNicNumber(nicNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "nicNumber", nicNumber)
        );
        return LoanMapper.INSTANCE.toDto(loans);
    }

    @Override
    public boolean updateLoan(LoanDto loansDto) {
        Loan loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoanMapper.INSTANCE.toEntity(loansDto);
        loansRepository.save(loans);
        return  true;
    }

    @Override
    public boolean deleteLoan(String nicNumber) {
        Loan loans = loansRepository.findByNicNumber(nicNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "nicNumber", nicNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}
