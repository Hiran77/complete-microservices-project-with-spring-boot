package com.savindu.loanManagement.repository;


import com.savindu.loanManagement.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByNicNumber(String nicNumber);

    Optional<Loan> findByLoanNumber(String loanNumber);

}